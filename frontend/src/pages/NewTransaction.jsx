import ItemTransactionService from "../services/ItemTransactionService";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import {useSelector} from "react-redux";
import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import React from "react";
import EmployeeService from "../services/EmployeeService";

export default function NewTransaction() {
    //TODO: GET EMPLOYEE FEE FROM EMPLOYEE, WITHDRAW FROM BALANCE

    // const employeeService = new EmployeeService();

    const transactionService = new ItemTransactionService();

    const userProps = useSelector(state => state?.user?.userProps)

    // const expertiseOptions = [{key: 1, text: "Yes", value: true}, {key: 0, text: "No", value: false} ]
    //
    //
    //
    // const handleExpertiseChange = (value) => {
    //     if(value === true){
    //         formik.values.employeeFee = (formik.values.itemPrice*0.05)
    //     }
    // }

    const addTransactionInitial = {
        fkBuyerId: userProps?.user?.id,
        fkSellerId: 0,
        fkEmployeeId: 0,
        itemName: "",
        itemPrice: 0,
        employeeFee: 0,
        details: ""
    }

    const addTransactionSchema = Yup.object().shape({
        fkSellerId: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number"),
        fkEmployeeId: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number"),
        itemName: Yup.string().required("Required").max(100, "Maximum 100 Characters"),
        itemPrice: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number"),
        employeeFee: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number"),
        details: Yup.string().required("Required").max(10000, "Maximum 10000 Characters")
    });


    const formik = useFormik({
        enableReinitialize: true,
        initialValues: addTransactionInitial,
        validationSchema: addTransactionSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            transactionService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                formik.resetForm({...addTransactionInitial})
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
                <Image
                    src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                New Transaction
            </Header>
            <Form onSubmit={formik.handleSubmit}>
                <Segment>
                    <Grid stackable>
                        <Grid.Column width={8}>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Seller ID</b></label>
                                <Form.Input
                                    fluid
                                    icon="user"
                                    iconPosition="left"
                                    placeholder="0"
                                    type="number"
                                    name="fkSellerId"
                                    value={formik.values.fkSellerId}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.fkSellerId && formik.touched.fkSellerId && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.fkSellerId}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Employee ID</b></label>
                                <Form.Input
                                    fluid
                                    icon="user"
                                    iconPosition="left"
                                    placeholder="0"
                                    type="number"
                                    name="fkEmployeeId"
                                    value={formik.values.fkEmployeeId}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.fkEmployeeId && formik.touched.fkEmployeeId && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.fkEmployeeId}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Item Name</b></label>
                                <Form.Input
                                    fluid
                                    icon="gem"
                                    iconPosition="left"
                                    placeholder="Item Name"
                                    type="text"
                                    name="itemName"
                                    value={formik.values.itemName}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.itemName && formik.touched.itemName && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.itemName}
                                    </div>
                                )}
                            </div>
                        </Grid.Column>

                        <Grid.Column width={8}>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Item Price</b></label>
                                <Form.Input
                                    fluid
                                    icon="money"
                                    iconPosition="left"
                                    placeholder="0"
                                    type="number"
                                    name="itemPrice"
                                    value={formik.values.itemPrice}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.itemPrice && formik.touched.itemPrice && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.itemPrice}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Employee Fee</b></label>
                                <Form.Input
                                    fluid
                                    icon="money"
                                    iconPosition="left"
                                    placeholder="0"
                                    type="number"
                                    name="employeeFee"
                                    value={formik.values.employeeFee}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                />
                                {formik.errors.employeeFee && formik.touched.employeeFee && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.employeeFee}
                                    </div>
                                )}
                            </div>
                            <div style={{marginTop: "1em"}}>
                                <label><b>Details</b></label>
                                <Form.TextArea
                                    placeholder="Details"
                                    type="text"
                                    name="details"
                                    value={formik.values.details}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}

                                />
                                {formik.errors.details && formik.touched.details && (
                                    <div className={"ui pointing red basic label"}>
                                        {formik.errors.details}
                                    </div>
                                )}
                            </div>
                        </Grid.Column>
                        {/*<Grid.Row centered>*/}
                        {/*    <div style={{marginTop: "1em"}}>*/}
                        {/*        <label><b>Expertise</b></label>*/}
                        {/*        <Form.Select*/}
                        {/*            name="expertise"*/}
                        {/*            placeholder="Expertise"*/}
                        {/*            options={expertiseOptions}*/}
                        {/*            onChange={(event, data) => handleExpertiseChange(data.value)}*/}
                        {/*        />*/}
                        {/*    </div>*/}
                        {/*</Grid.Row>*/}
                    </Grid>
                    <br/>
                    <Button color="black" fluid size="large" type="submit">
                        Save
                    </Button>
                </Segment>
            </Form>
        </div>
    )
}