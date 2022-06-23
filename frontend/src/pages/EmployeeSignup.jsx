import {Link, useHistory} from "react-router-dom";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import {Button, Form, Grid, GridColumn, Header, Image, Message, Segment} from "semantic-ui-react";
import React, {useEffect, useState} from "react";
import EmployeeService from "../services/EmployeeService";
import CityService from "../services/CityService";
import TownService from "../services/TownService";

export default function EmployeeSignup() {

    const employeeService = new EmployeeService();
    const cityService = new CityService();
    const townService = new TownService();

    const history = useHistory();

    const [cities, setCities] = useState([]);
    const [cityId, setCityId] = useState(0)

    const [towns, setTowns] = useState([]);
    const [townId, setTownId] = useState(0);

    useEffect(() => {
        cityService.getAll().then(result => setCities(result.data.data))

    },)

    useEffect(()=>{
        if(cityId !== 0){
            townService.getByCityId(cityId).then(result => setTowns(result.data.data))
        }
    },[cityId])

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
        console.log("City Id: " + value)
    }
    const handleChangeTown = (value) => {
        setTownId(value)
        formik.values.fkTownId = value
        console.log("Town Id: " + value)
    }

    const customerSignupSchema = Yup.object().shape({
        name: Yup.string().required("Required").max(50, "Maximum 50 characters"),
        surname: Yup.string().required("Required").max(50, "Maximum 50 characters"),
        phoneNumber: Yup.string().required("Required").matches(/\d{10}/, "Invalid Phone Number"),
        email: Yup.string().required("Required").max(100, "Maximum 100 Characters").email("Invalid E-Mail Format"),
        password: Yup.string().required("Required").min(6, "Minimum 6 Characters").max(100, "Maximum 100 Characters"),
        rePassword: Yup.string().oneOf([Yup.ref("password"), null], "Not matching").required("Required")
    });

    const formik = useFormik({
        initialValues: {
            name: "",
            surname: "",
            phoneNumber: "",
            email: "",
            password: "",
            rePassword: "",
            fkCityId: 0,
            fkTownId: 0
        },
        validationSchema: customerSignupSchema,
        onSubmit: (values) => {
            console.log(values)
            employeeService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                history.push("/login/employee")
            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });

    return (
        <div>
            <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                <Image src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                Employee Sign Up
            </Header>
            <Form onSubmit={formik.handleSubmit}>
                <Segment>
                    <Grid stackable>
                        <Grid.Column width={8}>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Name</b></label>

                                <Form.Input
                                    fluid
                                    icon="user"
                                    iconPosition="left"
                                    placeholder="Name"
                                    type="text"
                                    name="name"
                                    value={formik.values.name}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {
                                    formik.errors.name && formik.touched.name && (
                                        <div className={"ui pointing red basic label"}>
                                            {formik.errors.name}
                                        </div>
                                    )
                                }
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Surname</b></label>
                                <Form.Input
                                    fluid
                                    icon="user"
                                    iconPosition="left"
                                    placeholder="Surname"
                                    type="text"
                                    name="surname"
                                    value={formik.values.surname}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.surname && formik.touched.surname && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.surname}
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

                        <Grid.Column width={8}>
                            <div style={{marginTop: "1em"}}>
                                <label><b>E-Mail</b></label>
                                <Form.Input
                                    fluid
                                    icon="mail"
                                    iconPosition="left"
                                    placeholder="E-Mail Address"
                                    type="email"
                                    name="email"
                                    value={formik.values.email}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}

                                />
                                {formik.errors.email && formik.touched.email && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.email}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Phone Number (Without Leading 0)</b></label>
                                <Form.Input
                                    fluid
                                    icon="phone"
                                    iconPosition="left"
                                    placeholder="Phone Number"
                                    type="text"
                                    name="phoneNumber"
                                    value={formik.values.phoneNumber}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.phoneNumber && formik.touched.phoneNumber && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.phoneNumber}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Password</b></label>
                                <Form.Input
                                    fluid
                                    icon="lock"
                                    iconPosition="left"
                                    placeholder="Password"
                                    type="password"
                                    name="password"
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.password && formik.touched.password && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.password}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Repeat Password</b></label>
                                <Form.Input
                                    fluid
                                    icon="lock"
                                    iconPosition="left"
                                    placeholder="Repeat Password"
                                    type="password"
                                    name="rePassword"
                                    value={formik.values.rePassword}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.rePassword && formik.touched.rePassword && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.rePassword}
                                    </div>
                                )}
                            </div>
                        </Grid.Column>
                    </Grid>

                    <br/>
                    <Button color="black" fluid size="large" type="submit">
                        Sign-up as an Employee
                    </Button>
                </Segment>
            </Form>
            <Grid textAlign={"center"} stackable style={{marginTop: "1em"}}>
                <GridColumn width={5}>
                    <Message color={"grey"}>Sign-Up as a <Link to={"/signup/customer"}><b>Customer</b></Link></Message>
                    <Message color={"grey"}>Already Signed Up? <Link to={"/login/employee"}><b>Login Instead</b></Link></Message>
                </GridColumn>
            </Grid>
        </div>
    );
}