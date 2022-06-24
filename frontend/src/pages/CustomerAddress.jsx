import {Grid} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";

export default function CustomerAddress(){
    return(
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "8.5em"}}>
                    <CustomerSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "4em"}}>
                    Customer Address Settings
                </Grid.Column>
            </Grid>
        </div>
    )
}