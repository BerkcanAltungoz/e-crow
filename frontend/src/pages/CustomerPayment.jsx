import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import CustomerService from "../services/CustomerService";
import {useSelector} from "react-redux";
import {useHistory} from "react-router-dom";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import PaymentMethodService from "../services/PaymentMethodService";
import React from "react";

export default function CustomerPayment() {
    const paymentService = new PaymentMethodService();
    const userProps = useSelector(state => state?.user?.userProps)
    const history = useHistory();

    const initial = {
        fkCustomerId: userProps?.user?.id,
        nameOnCard: "",
        cardNumber: "",
        cvc: "",
        expiryDateMonth: 1,
        expiryDateYear: 1
    }
    const customerAccountSchema = Yup.object().shape({
        nameOnCard: Yup.string().required("Required").max(100, "Maximum 100 Characters"),
        cardNumber: Yup.string().required("Required").length(16, "Invalid Card Number"),
        cvc: Yup.string().required("Required").length(3, "Invalid CVC"),
        expiryDateMonth: Yup.number().integer("Must be an Integer").min(1, "Invalid Month").max(12, "Invalid Month"),
        expiryDateYear: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number")
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: initial,
        validationSchema: customerAccountSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            paymentService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                history.push("/customer/payment")
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
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "2em"}}>
                    <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        New Payment Method
                    </Header>
                    <Form onSubmit={formik.handleSubmit}>
                        <Segment>
                            <Grid stackable>
                                <Grid.Column>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Name on Card</b></label>
                                        <Form.Input
                                            fluid
                                            icon="user"
                                            iconPosition="left"
                                            placeholder="Name on Card"
                                            type="text"
                                            name="nameOnCard"
                                            value={formik.values.nameOnCard}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {
                                            formik.errors.nameOnCard && formik.touched.nameOnCard && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.nameOnCard}
                                                </div>
                                            )
                                        }
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Card Number</b></label>
                                        <Form.Input
                                            fluid
                                            icon="credit card outline"
                                            iconPosition="left"
                                            placeholder="Card Number"
                                            type="text"
                                            name="cardNumber"
                                            value={formik.values.cardNumber}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.cardNumber && formik.touched.cardNumber && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.cardNumber}
                                            </div>
                                        )}
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>CVC</b></label>
                                        <Form.Input
                                            fluid
                                            icon="cc mastercard"
                                            iconPosition="left"
                                            placeholder="CVC"
                                            type="text"
                                            name="cvc"
                                            value={formik.values.cvc}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.cvc && formik.touched.cvc && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.cvc}
                                            </div>
                                        )}
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Expiry Month</b></label>
                                        <Form.Input
                                            fluid
                                            icon="calendar"
                                            iconPosition="left"
                                            placeholder="Expiry Month"
                                            type="number"
                                            name="expiryDateMonth"
                                            value={formik.values.expiryDateMonth}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.expiryDateMonth && formik.touched.expiryDateMonth && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.expiryDateMonth}
                                            </div>
                                        )}
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Expiry Year</b></label>
                                        <Form.Input
                                            fluid
                                            icon="calendar alternate"
                                            iconPosition="left"
                                            placeholder="Expiry Year"
                                            type="number"
                                            name="expiryDateYear"
                                            value={formik.values.expiryDateYear}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.expiryDateYear && formik.touched.expiryDateYear && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.expiryDateYear}
                                            </div>
                                        )}
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