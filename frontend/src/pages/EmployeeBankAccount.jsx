import {Grid} from "semantic-ui-react";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";

export default function EmployeeBankAccount(){
    return(
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <EmployeeSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "4em"}}>
                    Employee Bank Account
                </Grid.Column>
            </Grid>
        </div>
    )
}