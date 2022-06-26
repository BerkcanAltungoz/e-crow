import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";
import EmployeeService from "../services/EmployeeService";
import CityService from "../services/CityService";
import TownService from "../services/TownService";
import React, {useEffect, useState} from "react";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import {useDispatch, useSelector} from "react-redux";
import {employeeLogin} from "../store/actions/userActions";

export default function EmployeeDetails() {
    const employeeService = new EmployeeService();
    const cityService = new CityService();
    const townService = new TownService();

    const userProps = useSelector(state => state?.user?.userProps)
    const dispatch = useDispatch();

    const [cities, setCities] = useState([]);
    const [cityId, setCityId] = useState(0)

    const [towns, setTowns] = useState([]);
    const [townId, setTownId] = useState(0);

    useEffect(() => {
        cityService.getAll().then(result => setCities(result.data.data))
    }, [])

    useEffect(() => {
        if (cityId !== 0) {
            townService.getByCityId(cityId).then(result => setTowns(result.data.data))
        }
    }, [cityId])

    const cityOptions = [];
    cities.map((city) => (cityOptions.push({
        key: city.id,
        text: city.name,
        value: city.id,
    })));
    const townOptions = [];
    towns.map((town) => (townOptions.push({
        key: town.id,
        text: town.name,
        value: town.id,
    })));

    const availableOptions = [{key: 1, text: "Available", value: true}, {key: 0, text: "Unavailable", value: false} ]

    const handleChangeCity = (value) => {
        setCityId(value)
        formik.values.fkCityId = value
        console.log("City Id: " + value)
    }
    const handleChangeTown = (value) => {
        setTownId(value)
        formik.values.fkTownId = value
        console.log("Town Id: " + value)
    }

    //TODO: FIX AVAILABLE
    const handleChangeAvailable = (value) => {
        formik.values.available = value
        console.log(formik.values.available)
        console.log("Available: " + value)
    }
    const initial = {
        id: userProps?.user?.id,
        expertise: userProps?.user?.expertise,
        fee: userProps?.user?.fee,
        expertiseFee: userProps?.user?.expertiseFee,
        available: userProps?.user?.available,
        description: userProps?.user?.description,
        fkCityId: userProps?.user?.fkCity?.id,
        fkTownId: userProps?.user?.fkTown?.id
    }
    const employeeDetailsSchema = Yup.object().shape({
        expertise: Yup.string().max(200, "Maximum 200 Characters"),
        fee: Yup.number().integer("Not an Integer").min(0, "Must be 0 or Positive"),
        expertiseFee: Yup.number().integer("Not an Integer").min(0, "Must be 0 or Positive"),
        description: Yup.string().required("Required").max(10000, "Maximum 10000 Characters"),
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: initial,
        validationSchema: employeeDetailsSchema,
        onSubmit: (values) => {
            console.log(values)
            employeeService.updateDetails(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)

                //TODO: MAKE THIS PERSIST

                // dispatch(employeeLogin(result.data.data))

                formik.resetForm({...initial});
            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });
    return (
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <EmployeeSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "2em"}}>
                    <div>
                        <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                            <Image
                                src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                            Account Details
                        </Header>
                        <Form onSubmit={formik.handleSubmit}>
                            <Segment>
                                <Grid stackable>
                                    <Grid.Column width={8}>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>Default Fee</b></label>
                                            <Form.Input
                                                fluid
                                                icon="money"
                                                iconPosition="left"
                                                placeholder="Default Fee"
                                                type="number"
                                                name="fee"
                                                value={formik.values.fee}
                                                onChange={formik.handleChange}
                                                onBlur={formik.handleBlur}
                                            />
                                            {formik.errors.fee && formik.touched.fee && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.fee}
                                                </div>
                                            )}
                                        </div>
                                        <div style={{marginTop: "3.9em"}}>
                                            <label><b>Expertise</b></label>
                                            <Form.Input
                                                fluid
                                                icon="clipboard outline"
                                                iconPosition="left"
                                                placeholder="Expertise"
                                                type="text"
                                                name="expertise"
                                                value={formik.values.expertise}
                                                onChange={formik.handleChange}
                                                onBlur={formik.handleBlur}
                                            />
                                            {
                                                formik.errors.expertise && formik.touched.expertise && (
                                                    <div className={"ui pointing red basic label"}>
                                                        {formik.errors.expertise}
                                                    </div>
                                                )
                                            }
                                        </div>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>Expertise Fee</b></label>
                                            <Form.Input
                                                fluid
                                                icon="money bill alternate outline"
                                                iconPosition="left"
                                                placeholder="Default Fee"
                                                type="number"
                                                name="expertiseFee"
                                                value={formik.values.expertiseFee}
                                                onChange={formik.handleChange}
                                                onBlur={formik.handleBlur}
                                            />
                                            {formik.errors.expertiseFee && formik.touched.expertiseFee && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.expertiseFee}
                                                </div>
                                            )}
                                        </div>
                                    </Grid.Column>

                                    <Grid.Column width={8}>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>Description</b></label>
                                            <Form.TextArea
                                                placeholder="Description"
                                                type="text"
                                                name="description"
                                                value={formik.values.description}
                                                onChange={formik.handleChange}
                                                onBlur={formik.handleBlur}

                                            />
                                            {formik.errors.description && formik.touched.description && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.description}
                                                </div>
                                            )}
                                        </div>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>City</b></label>
                                            <Form.Select
                                                name="city"
                                                placeholder="City"
                                                options={cityOptions}
                                                onChange={(event, data) => handleChangeCity(data.value)}
                                            />

                                        </div>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>Town</b></label>
                                            <Form.Select
                                                disabled={cityId === 0}
                                                name="town"
                                                placeholder="Town"
                                                options={townOptions}
                                                onChange={(event, data) => handleChangeTown(data.value)}
                                            />
                                        </div>
                                    </Grid.Column>
                                    <Grid.Row centered>
                                        <div style={{marginTop: "1em"}}>
                                            <label><b>Available</b></label>
                                            <Form.Select
                                                name="available"
                                                placeholder="Availability"
                                                options={availableOptions}
                                                onChange={(event, data) => handleChangeAvailable(data.value)}
                                            />
                                        </div>
                                    </Grid.Row>
                                </Grid>
                                <br/>
                                <Button color="black" fluid size="large" type="submit">
                                    Save
                                </Button>
                            </Segment>
                        </Form>
                    </div>
                </Grid.Column>
            </Grid>
        </div>
    )
}