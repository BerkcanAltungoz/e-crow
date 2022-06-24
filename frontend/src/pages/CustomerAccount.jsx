import {Link, useHistory, useLocation} from "react-router-dom";
import {Grid, Icon, Menu} from 'semantic-ui-react'
import CustomerService from "../services/CustomerService";
import * as Yup from "yup";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";

export default function CustomerAccount() {


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

    return (
        <div align={"center"} style={{marginBottom: "10em", marginTop: "7em"}}>
            <Grid stackable>
                <Grid.Column width={4}>
                    <CustomerSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12}>

                </Grid.Column>
            </Grid>
        </div>
    );

}