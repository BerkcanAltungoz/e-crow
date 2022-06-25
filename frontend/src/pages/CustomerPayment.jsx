import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import {useDispatch, useSelector} from "react-redux";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import PaymentMethodService from "../services/PaymentMethodService";
import React, {useEffect, useState} from "react";
import CustomerService from "../services/CustomerService";
import {userUpdateBalance} from "../store/actions/userActions";

export default function CustomerPayment() { //TODO: SHOW BILLING HISTORY
    const paymentService = new PaymentMethodService();
    const customerService = new CustomerService();
    const userProps = useSelector(state => state?.user?.userProps)
    const dispatch = useDispatch();

    const [paymentMethods,setPaymentMethods] = useState([])
    const [paymentMethodId, setPaymentMethodId] = useState(0)

    useEffect(() => {
        if(typeof userProps?.user?.id !== 'undefined') {
            paymentService.getByCustomerId(userProps?.user?.id).then(result => setPaymentMethods(result.data.data))
        }
    }, [userProps?.user?.id])

    const paymentOptions = [];
    paymentMethods.map((paymentMethod) => (paymentOptions.push({
        key: paymentMethod.id,
        text: paymentMethod.nameOnCard + " - " +paymentMethod.cardNumber,
        value: paymentMethod.id,
    })));

    const handleChangePaymentOption = (value) => {
        setPaymentMethodId(value)
        addBalanceFormik.values.fkPaymentMethodId = value
    }


    const addBalanceInitial = {
        fkCustomerId: userProps?.user?.id,
        fkPaymentMethodId: 0,
        depositAmount: 0
    }

    const addBalanceSchema = Yup.object().shape({
        depositAmount: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number")
    });


    const addBalanceFormik = useFormik({
        enableReinitialize: true,
        initialValues: addBalanceInitial,
        validationSchema: addBalanceSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            customerService.depositBalance(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                // history.push("/customer/payment")
                addBalanceFormik.resetForm({...addBalanceInitial});
                dispatch(userUpdateBalance(result.data.data))


            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });

    const addPaymentInitial = {
        fkCustomerId: userProps?.user?.id,
        nameOnCard: "",
        cardNumber: "",
        cvc: "",
        expiryDateMonth: 1,
        expiryDateYear: 1
    }
    const addPaymentSchema = Yup.object().shape({
        nameOnCard: Yup.string().required("Required").max(100, "Maximum 100 Characters"),
        cardNumber: Yup.string().required("Required").length(16, "Invalid Card Number"),
        cvc: Yup.string().required("Required").length(3, "Invalid CVC"),
        expiryDateMonth: Yup.number().integer("Must be an Integer").min(1, "Invalid Month").max(12, "Invalid Month"),
        expiryDateYear: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number")
    });



    const addPaymentFormik = useFormik({
        enableReinitialize: true,
        initialValues: addPaymentInitial,
        validationSchema: addPaymentSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            paymentService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                addPaymentFormik.resetForm({...addPaymentInitial})
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
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "3.25em"}}>
                    <Header as="h2" color="black" textAlign="center" style={{ marginBottom: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Deposit Balance
                    </Header>

                    <Form onSubmit={addBalanceFormik.handleSubmit}>
                        <Segment>
                            <Grid stackable>
                                <Grid.Column>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Payment Method</b></label>
                                        <Form.Select
                                            name="paymentMethod"
                                            placeholder="Payment Method"
                                            options={paymentOptions}
                                            onChange={(event, data) => handleChangePaymentOption(data.value)}
                                        />
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Deposit Amount</b></label>
                                        <Form.Input
                                            fluid
                                            icon="money"
                                            iconPosition="left"
                                            placeholder="0"
                                            type="number"
                                            name="depositAmount"
                                            value={addBalanceFormik.values.depositAmount}
                                            onChange={addBalanceFormik.handleChange}
                                            onBlur={addBalanceFormik.handleBlur}
                                        />
                                        {addBalanceFormik.errors.depositAmount && addBalanceFormik.touched.depositAmount && (
                                            <div className={"ui pointing red basic label"}>
                                                {addBalanceFormik.errors.depositAmount}
                                            </div>
                                        )}
                                    </div>
                                </Grid.Column>
                            </Grid>
                            <br/>
                            <Button color="black" fluid size="large" type="submit">
                                Deposit
                            </Button>
                        </Segment>
                    </Form>

                    <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Add Payment Method
                    </Header>
                    <Form onSubmit={addPaymentFormik.handleSubmit}>
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
                                            value={addPaymentFormik.values.nameOnCard}
                                            onChange={addPaymentFormik.handleChange}
                                            onBlur={addPaymentFormik.handleBlur}
                                        />
                                        {
                                            addPaymentFormik.errors.nameOnCard && addPaymentFormik.touched.nameOnCard && (
                                                <div className={"ui pointing red basic label"}>
                                                    {addPaymentFormik.errors.nameOnCard}
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
                                            value={addPaymentFormik.values.cardNumber}
                                            onChange={addPaymentFormik.handleChange}
                                            onBlur={addPaymentFormik.handleBlur}
                                        />
                                        {addPaymentFormik.errors.cardNumber && addPaymentFormik.touched.cardNumber && (
                                            <div className={"ui pointing red basic label"}>
                                                {addPaymentFormik.errors.cardNumber}
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
                                            value={addPaymentFormik.values.cvc}
                                            onChange={addPaymentFormik.handleChange}
                                            onBlur={addPaymentFormik.handleBlur}
                                        />
                                        {addPaymentFormik.errors.cvc && addPaymentFormik.touched.cvc && (
                                            <div className={"ui pointing red basic label"}>
                                                {addPaymentFormik.errors.cvc}
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
                                            value={addPaymentFormik.values.expiryDateMonth}
                                            onChange={addPaymentFormik.handleChange}
                                            onBlur={addPaymentFormik.handleBlur}
                                        />
                                        {addPaymentFormik.errors.expiryDateMonth && addPaymentFormik.touched.expiryDateMonth && (
                                            <div className={"ui pointing red basic label"}>
                                                {addPaymentFormik.errors.expiryDateMonth}
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
                                            value={addPaymentFormik.values.expiryDateYear}
                                            onChange={addPaymentFormik.handleChange}
                                            onBlur={addPaymentFormik.handleBlur}
                                        />
                                        {addPaymentFormik.errors.expiryDateYear && addPaymentFormik.touched.expiryDateYear && (
                                            <div className={"ui pointing red basic label"}>
                                                {addPaymentFormik.errors.expiryDateYear}
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