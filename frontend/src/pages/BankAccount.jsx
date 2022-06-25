import {Button, Card, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import {useSelector} from "react-redux";
import BankInformationService from "../services/BankInformationService";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import React, {useEffect, useState} from "react";

export default function BankAccount(){
    const bankInformationService = new BankInformationService();

    const userProps = useSelector(state => state?.user?.userProps)


    const [bankAccounts, setBankAccounts] = useState([]);

    useEffect(() => {
        if(typeof userProps?.user?.id !== 'undefined') {
            bankInformationService.getByUserId(userProps?.user?.id).then(result => setBankAccounts(result.data.data))
        }
    }, [userProps?.user?.id])

    const initial = {
        fkUserId: userProps?.user?.id,
        nickname: "",
        iban: "",

    }
    const customerAccountSchema = Yup.object().shape({
        nickname: Yup.string().required("Required").max(50, "Maximum 50 Characters"),
        iban: Yup.string().required("Required").length(26, "Invalid IBAN"),
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: initial,
        validationSchema: customerAccountSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            bankInformationService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                // history.push("/bankAccount")
                window.location.reload()
                formik.resetForm({...initial});
            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });
    return(
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    {userProps.userType === 1 && <CustomerSettingCategories/>}
                    {userProps.userType === 2 && <EmployeeSettingCategories/>}
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "3.4em"}}>
                    <Header as="h2" color="black" textAlign="center" style={{ marginBottom: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        My Bank Accounts
                    </Header>
                    <Card.Group >
                        {bankAccounts.map(bankAccount => (
                            <Card key={bankAccount.id} color={"black"} fluid>
                                <Card.Content>
                                    <Card.Header>{bankAccount.nickname}</Card.Header>
                                    <Card.Description>{bankAccount.iban}</Card.Description>
                                </Card.Content>
                            </Card>
                        ))}
                    </Card.Group>


                    <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Add Bank Account
                    </Header>
                    <Form onSubmit={formik.handleSubmit}>
                        <Segment>
                            <Grid stackable>
                                <Grid.Column>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Bank Nickname</b></label>
                                        <Form.Input
                                            fluid
                                            icon="building"
                                            iconPosition="left"
                                            placeholder="Bank Nickname"
                                            type="text"
                                            name="nickname"
                                            value={formik.values.nickname}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {
                                            formik.errors.nickname && formik.touched.nickname && (
                                                <div className={"ui pointing red basic label"}>
                                                    {formik.errors.nickname}
                                                </div>
                                            )
                                        }
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>IBAN</b></label>
                                        <Form.Input
                                            fluid
                                            icon="clipboard"
                                            iconPosition="left"
                                            placeholder="IBAN"
                                            type="text"
                                            name="iban"
                                            value={formik.values.iban}
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                        />
                                        {formik.errors.iban && formik.touched.iban && (
                                            <div className={"ui pointing red basic label"}>
                                                {formik.errors.iban}
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