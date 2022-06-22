import {Container, Icon, Menu} from "semantic-ui-react";
import {Link} from "react-router-dom";
import SignedOut from "./SignedOut";

export default function Navi() {
    return (
        <div>
            <Menu inverted stackable>
                <Container>
                    <Menu.Item name="Home" as={Link} to={"/"}>
                        <Icon name="home"/>
                        {/*<Image src={"https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"}/>*/}
                        E-Crow
                    </Menu.Item>

                    <Menu.Menu position="right">
                        {/*{authItem[0].loggedIn && authItem[0].user.userType===2 &&  <Button primary as={Link} to={"/jobAdCreate"}>*/}
                        {/*    İlan Ekle*/}
                        {/*</Button>}*/}
                        {/*{authItem[0].loggedIn && authItem[0].user.userType===1 &&  <Button color="red" as={Link} to={`/jobAdFavorites`}>*/}
                        {/*    <Icon name='heart' />*/}
                        {/*    Favori İlanlar*/}
                        {/*</Button>}*/}

                        {/*{authItem[0].loggedIn?<SingedIn/>:<SingedOut/>}*/}
                        <Menu.Item>
                            <SignedOut/>
                        </Menu.Item>
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}