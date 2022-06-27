import {Button, Form, Grid, Header, Image, Segment} from 'semantic-ui-react'
import CustomerService from "../services/CustomerService";
import * as Yup from "yup";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import {useDispatch, useSelector} from "react-redux";
import React from "react";
import {customerLogin} from "../store/actions/userActions";

export default function CustomerAccount() {

    const customerService = new CustomerService();
    const dispatch = new useDispatch();
    const userProps = useSelector(state => state?.user?.userProps)

    const initial = {
        id: userProps?.user?.id,
        name: userProps?.user?.name,
        surname: userProps?.user?.surname,
        phoneNumber: userProps?.user?.phoneNumber,
        email: userProps?.user?.email,
        password: "",
        rePassword: ""
    }
    const customerAccountSchema = Yup.object().shape({
        name: Yup.string().max(50, "Maximum 50 Characters"),
        surname: Yup.string().max(50, "Maximum 50 Characters"),
        phoneNumber: Yup.string().matches(/\d{10}/, "Invalid Phone Number").length(10, "Invalid Phone Number"),
        email: Yup.string().max(100, "Maximum 100 Characters").email("Invalid E-Mail Format"),
        password: Yup.string().required("Required").min(6, "Minimum 6 Characters").max(100, "Maximum 100 Characters"),
        rePassword: Yup.string().oneOf([Yup.ref("password"), null], "Not matching").required("Required")
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: initial,
        validationSchema: customerAccountSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            customerService.updateBase(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)

                //TODO: MAKE THIS PERSIST
                dispatch(customerLogin(result.data.data))
                localStorage.setItem("customer", JSON.stringify(result.data.data))
                formik.resetForm({...initial});
            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });
    return (
        <div align={"center"} >
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <CustomerSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "4em"}}>
                    <Header as="h2" color="black" textAlign="center">
                        <Image src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Update Account Information
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
                                Update
                            </Button>
                        </Segment>
                    </Form>
                </Grid.Column>
            </Grid>
        </div>
    );

}