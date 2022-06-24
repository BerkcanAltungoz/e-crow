import {Container, Icon, Menu} from "semantic-ui-react";
import {Link} from "react-router-dom";
import SignedOut from "./SignedOut";
import SignedIn from "./SignedIn";
import {useSelector} from "react-redux";

export default function Navi() {
    const userProps = useSelector(state => state?.user?.userProps)
    return (
        <div>
            <Menu inverted stackable size={"huge"}>
                <Container>
                    <Menu.Item name="Home" as={Link} to={"/"}>
                        <Icon name="home"/>
                        {/*<Image size={"mini"} src={"https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"}/>*/}
                        E-Crow
                    </Menu.Item>
                    {userProps.loggedIn &&
                        <Menu.Item as={Link} to={"/messages"}>Messages</Menu.Item>
                    }
                    {userProps.loggedIn &&
                        <Menu.Item>Balance: {userProps?.user?.balance} $</Menu.Item>
                    }
                    <Menu.Menu position={"right"}>
                        {userProps.loggedIn ? <SignedIn/> : <SignedOut/>}
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}