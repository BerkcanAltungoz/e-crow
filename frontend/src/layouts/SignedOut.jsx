import {Button, Grid, Header, Icon, MenuItem, Modal} from "semantic-ui-react";
import {Link} from "react-router-dom";
import {useState} from "react";

export default function SignedOut() {
    const [loginOpen, setLoginOpen] = useState(false);
    const [signupOpen, setSignupOpen] = useState(false);

    const handleLoginModal = (value) => {
        setLoginOpen(value);
    };

    const handleSignupModal = (value) => {
        setSignupOpen(value);
    };

    return (
        <div>
            <MenuItem fitted={"vertically"}>
                <MenuItem onClick={() => handleLoginModal(true)}>
                    Log-In
                </MenuItem>

                <MenuItem onClick={() => handleSignupModal(true)}>
                    Sign Up
                </MenuItem>
            </MenuItem>



            <Modal
                basic
                dimmer
                onClose={() => handleLoginModal(false)}
                onOpen={() => handleLoginModal(true)}
                open={loginOpen}
                size="small"
            >
                <Header icon as="h2">
                    <Icon name="sign-in"/>
                    Log-in as
                </Header>

                <Modal.Actions>
                    <Grid>
                        <Grid.Row>
                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Customer"
                                    as={Link}
                                    to={"/login/customer"}
                                    onClick={() => setLoginOpen(false)}
                                ></Button>
                            </Grid.Column>

                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Employee"
                                    as={Link}
                                    to={"/login/employee"}
                                    onClick={() => setLoginOpen(false)}
                                ></Button>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Modal.Actions>
            </Modal>

            <Modal
                basic
                dimmer
                onClose={() => handleSignupModal(false)}
                onOpen={() => handleSignupModal(true)}
                open={signupOpen}
                size="small"
            >
                <Header icon as="h2">
                    <Icon name="signup"/>
                    Create Account as ?
                </Header>

                <Modal.Actions>
                    <Grid>
                        <Grid.Row>
                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Customer"
                                    as={Link}
                                    to={"/signup/customer"}
                                    onClick={() => setSignupOpen(false)}
                                />
                            </Grid.Column>
                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Employee"
                                    as={Link}
                                    to={"/signup/employee"}
                                    onClick={() => setSignupOpen(false)}
                                />
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Modal.Actions>
            </Modal>
        </div>

    );
}