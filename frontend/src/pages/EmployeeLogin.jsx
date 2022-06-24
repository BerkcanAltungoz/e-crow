import {useDispatch} from "react-redux";
import {Link, useHistory} from "react-router-dom";
import * as Yup from "yup";
import {useFormik} from "formik";
import {employeeLogin} from "../store/actions/userActions";
import {toast} from "react-toastify";
import {Button, Form, Grid, GridColumn, Header, Image, Message, Segment} from "semantic-ui-react";
import React from "react";
import EmployeeService from "../services/EmployeeService";

export default function EmployeeLogin(){
    const employeeService = new EmployeeService();

    const dispatch = useDispatch();
    const history = useHistory();

    const employeeLoginSchema = Yup.object().shape({
        email: Yup.string().required("Required").email("Invalid E-Mail Format"),
        password: Yup.string().required("Required")
    })

    const formik = useFormik({
        initialValues: {
            email: "",
            password: ""
        },
        validationSchema: employeeLoginSchema,
        onSubmit: (values) => {
            employeeService.signIn(values)
                .then((result) => {
                    dispatch(employeeLogin(result.data.data))
                    localStorage.setItem("employee", JSON.stringify(result.data.data))
                    history.push("/")
                    toast.success("Welcome")
                }).catch((result) => {
                toast.error(result.response.data.message)
            })
        }
    })
    return (
        <div>
            <div>
                <Grid textAlign="center" stackable>
                    <GridColumn width={9}>
                        <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                            <Image
                                src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                            Employee Log-in
                        </Header>

                        <Form size="large" onSubmit={formik.handleSubmit}>
                            <Segment>
                                <div>
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
                                    {
                                        formik.errors.email && formik.touched.email && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.email}
                                            </div>
                                        )
                                    }
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
                                    {
                                        formik.errors.password && formik.touched.password && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.password}
                                            </div>
                                        )
                                    }
                                </div>

                                <Button color="black" fluid size="large" type="submit" style={{marginTop: "1em"}}>
                                    Log-in
                                </Button>
                            </Segment>
                        </Form>
                        <Message color={"grey"}>Log-in as <Link to={"/login/customer"}><b>Customer</b></Link></Message>
                        <Message color={"grey"}>
                            Are You Not Signed Up? Sign Up as a
                            <Link to={"/signup/customer"}> <b>Customer</b> </Link>
                            or an
                            <Link to={"/signup/employee"}> <b>Employee</b></Link>.
                        </Message>
                    </GridColumn>
                </Grid>
            </div>
        </div>
    )
}