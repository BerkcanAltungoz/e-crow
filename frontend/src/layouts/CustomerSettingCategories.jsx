import {Icon, Menu} from "semantic-ui-react";
import {Link, useLocation} from "react-router-dom";

export default function CustomerSettingCategories(){

    const location = useLocation().pathname;

    return(
        <Menu fluid compact icon="labeled" vertical >
            <Menu.Item as={Link} to={"/customer/account"} active={location === "/customer/account"}>
                <Icon name="user"/>
                Account Information
            </Menu.Item>

            <Menu.Item as={Link} to={"/customer/address"} active={location === "/customer/address"}>
                <Icon name="home"/>
                Address Information
            </Menu.Item>

            <Menu.Item as={Link} to={"/customer/payment"} active={location === "/customer/payment"}>
                <Icon name="credit card"/>
                Payment and Balance
            </Menu.Item>

            <Menu.Item as={Link} to={"/customer/bankAccount"} active={location === "/customer/bankAccount"}>
                <Icon name="money"/>
                Bank Account
            </Menu.Item>
        </Menu>
    )
}