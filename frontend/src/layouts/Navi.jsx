import {Container, Icon, Image, Menu} from "semantic-ui-react";
import {Link} from "react-router-dom";
import SignedOut from "./SignedOut";
import {userProps} from "../store/initialValues/userProps";
import SignedIn from "./SignedIn";

export default function Navi() {
    return (
        <div>
            <Menu inverted stackable>
                <Container>
                    <Menu.Item name="Home" as={Link} to={"/"}>
                        <Icon name="home"/>
                        {/*<Image size={"mini"} src={"https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"}/>*/}
                        E-Crow
                    </Menu.Item>

                    <Menu.Item name={"Messages.jsx"} as={Link} to{"/Messages.jsx"}

                    <Menu.Menu position="right">
                        <Menu.Item>
                            {userProps.loggedIn ? <SignedIn/> : <SignedOut/>}
                        </Menu.Item>
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}