import {Button, Grid, Header, Icon, Modal} from "semantic-ui-react";
import {Link} from "react-router-dom";
import {useState} from "react";

export default function SignedOut() {
    const [customerOpen, setCustomerOpen] = useState(false);
    const [employeeOpen, setEmployeeOpen] = useState(false);

    const handleCustomerModal = (value) => {
        setCustomerOpen(value);
    };

    const handleEmployeeModal = (value) => {
        setEmployeeOpen(value);
    };

    return (
        <div>
            <Button
                circular
                content="Log-in"
                onClick={() => handleCustomerModal(true)}
            />

            <Button
                circular
                content="Sign up"
                onClick={() => handleEmployeeModal(true)}
            />

            <Modal
                basic
                dimmer
                onClose={() => handleCustomerModal(false)}
                onOpen={() => handleCustomerModal(true)}
                open={customerOpen}
                size="small"
            >
                <Header icon as="h2">
                    <Icon name="sign-in"/>
                    Log-in As
                </Header>

                <Modal.Actions>
                    <Grid>
                        <Grid.Row>
                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Candidate"
                                    as={Link}
                                    to={"/login/customer"}
                                    onClick={() => setCustomerOpen(false)}
                                ></Button>
                            </Grid.Column>

                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Employee"
                                    as={Link}
                                    to={"/login/employee"}
                                    onClick={() => setCustomerOpen(false)}
                                ></Button>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Modal.Actions>
            </Modal>

            <Modal
                basic
                dimmer
                onClose={() => handleEmployeeModal(false)}
                onOpen={() => handleEmployeeModal(true)}
                open={employeeOpen}
                size="small"
            >
                <Header icon as="h2" className="orbitron">
                    <Icon name="paper plane"/>
                    Create Account As ?
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
                                    onClick={() => setEmployeeOpen(false)}
                                />
                            </Grid.Column>
                            <Grid.Column width="8">
                                <Button
                                    circular
                                    fluid
                                    content="Employee"
                                    as={Link}
                                    to={"/signup/employee"}
                                    onClick={() => setEmployeeOpen(false)}
                                />
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Modal.Actions>
            </Modal>
        </div>

    );
}