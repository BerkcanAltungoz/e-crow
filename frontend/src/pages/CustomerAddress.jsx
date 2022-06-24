import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import {useSelector} from "react-redux";
import {useHistory} from "react-router-dom";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import AddressService from "../services/AddressService";
import CityService from "../services/CityService";
import TownService from "../services/TownService";
import React, {useEffect, useState} from "react";

export default function CustomerAddress() {
    const addressService = new AddressService();
    const cityService = new CityService();
    const townService = new TownService();

    const userProps = useSelector(state => state?.user?.userProps)
    const history = useHistory();

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

    const handleChangeCity = (value) => {
        setCityId(value)
        formik.values.fkCityId = value
        // console.log("City Id: " + value)
    }
    const handleChangeTown = (value) => {
        setTownId(value)
        formik.values.fkTownId = value
        // console.log("Town Id: " + value)
    }
    const initial = {
        fkCustomerId: userProps?.user?.id,
        fkCityId: 0,
        fkTownId: 0,
        postalCode: "",
        namesurname: "",
        addressLine: ""
    }
    const customerAddressSchema = Yup.object().shape({
        namesurname: Yup.string().required("Required").max(100, "Maximum 100 Characters"),
        addressLine: Yup.string().required("Required").max(1000, "Maximum 1000 Characters"),
        postalCode: Yup.string().required("Required").max(5,"Maximum 5 Characters").matches(/^\d{5}$/, "Invalid Postal Code")
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: initial,
        validationSchema: customerAddressSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            addressService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                history.push("/customer/address")
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
                    <CustomerSettingCategories/>
                </Grid.Column>
                <Grid.Column width={11} style={{marginBottom: "10em", marginTop: "2em"}}>
                    <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        New Address
                    </Header>
                    <Form onSubmit={formik.handleSubmit}>
                        <Segment>
                            <Grid stackable>
                                <Grid.Column>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Name Surname</b></label>

                                        <Form.Input
                                            fluid
                                            icon="user"
                                            iconPosition="left"
                                            placeholder="Name Surname"
                                            type="text"
                                            name="namesurname"
                                            value={formik.values.namesurname}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {
                                            formik.errors.namesurname && formik.touched.namesurname && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.namesurname}
                                                </div>
                                            )
                                        }
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Postal Code</b></label>
                                        <Form.Input
                                            fluid
                                            icon="address card"
                                            iconPosition="left"
                                            placeholder="Postal Code"
                                            type="text"
                                            name="postalCode"
                                            value={formik.values.postalCode}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.postalCode && formik.touched.postalCode && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.postalCode}
                                            </div>
                                        )}
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Address</b></label>
                                        <Form.TextArea
                                            placeholder="Address Line"
                                            type="text"
                                            name="addressLine"
                                            value={formik.values.addressLine}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.addressLine && formik.touched.addressLine && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.addressLine}
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
                            </Grid>

                            <br/>
                            <Button color="black" fluid size="large" type="submit">
                                Save
                            </Button>
                        </Segment>
                    </Form>
                </Grid.Column>
            </Grid>
        </div>
    )
}