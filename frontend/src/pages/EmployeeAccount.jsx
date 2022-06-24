import {useSelector} from "react-redux";
import {useHistory} from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import {Grid} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";

export default function EmployeeAccount(){
    const employeeService = new EmployeeService();
    const userProps = useSelector(state => state?.user?.userProps)
    const history = useHistory();
    return(
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <EmployeeSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "4em"}}>
                    Employee Account
                </Grid.Column>
            </Grid>
        </div>
    )

}