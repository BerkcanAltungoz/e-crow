import {Grid} from "semantic-ui-react";
import CustomerSettingCategories from "../layouts/CustomerSettingCategories";

export default function CustomerAddress(){
    return(
        <div align={"center"} style={{marginBottom: "10em", marginTop: "7em"}}>
            <Grid stackable>
                <Grid.Column width={4}>
                    <CustomerSettingCategories/>
                </Grid.Column>
                <Grid.Column width={12}>
                    Customer Address Settings
                </Grid.Column>
            </Grid>
        </div>
    )
}