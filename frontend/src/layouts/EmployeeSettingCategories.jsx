import {Link, useLocation} from "react-router-dom";
import {Icon, Menu} from "semantic-ui-react";

export default function EmployeeSettingCategories(){
    const location = useLocation().pathname;

    return(
        <Menu fluid compact icon="labeled" vertical >
            <Menu.Item as={Link} to={"/employee/account"} active={location === "/employee/account"}>
                <Icon name="user"/>
                Account Information
            </Menu.Item>

            <Menu.Item as={Link} to={"/employee/details"} active={location === "/employee/details"}>
                <Icon name="id card"/>
                Account Details
            </Menu.Item>

            <Menu.Item as={Link} to={"/bankAccount"} active={location === "/bankAccount"}>
                <Icon name="money"/>
                Bank Account
            </Menu.Item>
        </Menu>
    )
}