import React from "react";
import {Link, useHistory} from "react-router-dom";
import * as Yup from "yup";
import {Button, Form, Grid, GridColumn, Header, Image, Message, Segment} from "semantic-ui-react";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import CustomerService from "../services/CustomerService";

export default function CustomerSignup() {
    const customerService = new CustomerService();
    const history = useHistory();
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
            rePassword: ""
        },
        validationSchema: customerSignupSchema,
        onSubmit: (values) => {
            console.log(values)
            customerService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                history.push("/login/customer")
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
                Customer Sign Up
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
                                    value={formik.values.name}
                                    name="name"
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
                        </Grid.Column>

                        <Grid.Column width={8}>
                            <div style={{marginTop: "1em"}}>
                                <label><b>E-Mail</b></label>
                                <Form.Input
                                    fluid
                                    icon="at"
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
                        Sign-up as a Customer
                    </Button>
                </Segment>
            </Form>
            <Grid textAlign={"center"} stackable style={{marginTop: "1em"}}>
                <GridColumn width={5}>
                    <Message color={"grey"}>Sign-Up as an <Link to={"/signup/employee"}><b>Employee</b></Link></Message>
                    <Message color={"grey"}>Already Signed Up? <Link to={"/login/customer"}><b>Login Instead</b></Link></Message>
                </GridColumn>
            </Grid>
        </div>
    );
}