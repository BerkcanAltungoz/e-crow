import {Grid} from "semantic-ui-react";
import EmployeeSettingCategories from "../layouts/EmployeeSettingCategories";

export default function EmployeeDetails(){
    return (
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <EmployeeSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "4em"}}>
                    Employee Details
                </Grid.Column>
            </Grid>
        </div>
    )
}