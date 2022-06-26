import {Button, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import {useDispatch, useSelector} from "react-redux";
import BankInformationService from "../services/BankInformationService";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";
import * as Yup from "yup";
import {useFormik} from "formik";
import {toast} from "react-toastify";
import React, {useEffect, useState} from "react";
import {userUpdateBalance} from "../store/actions/userActions";
import BaseUserService from "../services/BaseUserService";

export default function BankAccount(){
    const bankInformationService = new BankInformationService();
    const userService = new BaseUserService();

    const userProps = useSelector(state => state?.user?.userProps)
    const dispatch = useDispatch()

    const [bankAccounts, setBankAccounts] = useState([]);
    const [bankAccountId, setBankAccountId] = useState([]);

    useEffect(() => {
        if(typeof userProps?.user?.id !== 'undefined') {
            bankInformationService.getByUserId(userProps?.user?.id).then(result => setBankAccounts(result.data.data))
        }
    }, [userProps?.user?.id])

    const bankOptions = [];
    bankAccounts.map((bankAccount) => (bankOptions.push({
        key: bankAccount.id,
        text: bankAccount.nickname + " - " +bankAccount.iban,
        value: bankAccount.id,
    })));

    const handleChangeBankOption = (value) => {
        setBankAccountId(value)
        withdrawBalanceFormik.values.fkBankInformationId = value
    }


    const withdrawBalanceInitial = {
        fkUserId: userProps?.user?.id,
        fkBankInformationId: 0,
        withdrawAmount: 0
    }

    const withdrawBalanceSchema = Yup.object().shape({
        withdrawAmount: Yup.number().integer("Must be an Integer").positive("Must be a Positive Number").lessThan(userProps?.user?.balance,"Insufficient Funds")
    });


    const withdrawBalanceFormik = useFormik({
        enableReinitialize: true,
        initialValues: withdrawBalanceInitial,
        validationSchema: withdrawBalanceSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            userService.withdrawBalance(values).then((result) => {
                console.log(result.data.message)
                console.log(result)
                toast.success(result.data.message)
                // history.push("/customer/payment")
                withdrawBalanceFormik.resetForm({...withdrawBalanceInitial});
                dispatch(userUpdateBalance(result.data.data))

               // TODO: REDUX PERSIST

               //  if(userProps?.userType === 1){
               //      localStorage.setItem("customer", JSON.stringify(userProps?.user))
               //  }
               //  else{
               //      localStorage.setItem("employee", JSON.stringify(userProps?.user))
               //  }
               //
               //  console.log(userProps?.userType)
               //  console.log(userProps?.user)


            })
                .catch((result) => {
                    console.log(result.response.data.message)
                    toast.error(result.response.data.message)
                })
        }
    });

    const bankInitial = {
        fkUserId: userProps?.user?.id,
        nickname: "",
        iban: "",
    }
    const bankSchema = Yup.object().shape({
        nickname: Yup.string().required("Required").max(50, "Maximum 50 Characters"),
        iban: Yup.string().required("Required").length(26, "Invalid IBAN"),
    });

    const bankFormik = useFormik({
        enableReinitialize: true,
        initialValues: bankInitial,
        validationSchema: bankSchema,
        onSubmit: (values) => {
            console.log(values)
            // console.log(userProps.user)
            bankInformationService.add(values).then((result) => {
                console.log(result.data.message)
                toast.success(result.data.message)
                // history.push("/bankAccount")
                bankFormik.resetForm({...bankInitial});
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
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "3.3em"}}>
                    <Header as="h2" color="black" textAlign="center" style={{ marginBottom: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Withdraw Balance
                    </Header>

                    <Form onSubmit={withdrawBalanceFormik.handleSubmit}>
                        <Segment>
                            <Grid stackable>
                                <Grid.Column>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Bank Account</b></label>
                                        <Form.Select
                                            name="bankInformation"
                                            placeholder="Bank Account"
                                            options={bankOptions}
                                            onChange={(event, data) => handleChangeBankOption(data.value)}
                                        />
                                    </div>
                                    <div style={{marginTop: "1em"}}>
                                        <label><b>Withdraw Amount</b></label>
                                        <Form.Input
                                            fluid
                                            icon="money"
                                            iconPosition="left"
                                            placeholder="0"
                                            type="number"
                                            name="withdrawAmount"
                                            value={withdrawBalanceFormik.values.withdrawAmount}
                                            onChange={withdrawBalanceFormik.handleChange}
                                            onBlur={withdrawBalanceFormik.handleBlur}
                                        />
                                        {withdrawBalanceFormik.errors.withdrawAmount && withdrawBalanceFormik.touched.withdrawAmount && (
                                            <div className={"ui pointing red basic label"}>
                                                {withdrawBalanceFormik.errors.withdrawAmount}
                                            </div>
                                        )}
                                    </div>
                                </Grid.Column>
                            </Grid>
                            <br/>
                            <Button color="black" fluid size="large" type="submit">
                                Withdraw
                            </Button>
                        </Segment>
                    </Form>

                    <Header as="h2" color="black" textAlign="center" style={{marginTop: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Add Bank Account
                    </Header>
                    <Form onSubmit={bankFormik.handleSubmit}>
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
                                            value={bankFormik.values.nickname}
                                            onChange={bankFormik.handleChange}
                                            onBlur={bankFormik.handleBlur}
                                        />
                                        {
                                            bankFormik.errors.nickname && bankFormik.touched.nickname && (
                                                <div className={"ui pointing red basic label"}>
                                                    {bankFormik.errors.nickname}
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
                                            value={bankFormik.values.iban}
                                            onChange={bankFormik.handleChange}
                                            onBlur={bankFormik.handleBlur}
                                        />
                                        {bankFormik.errors.iban && bankFormik.touched.iban && (
                                            <div className={"ui pointing red basic label"}>
                                                {bankFormik.errors.iban}
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