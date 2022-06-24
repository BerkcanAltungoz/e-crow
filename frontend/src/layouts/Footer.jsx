import React from "react";
import {Container, Grid, Header, List, Segment,} from "semantic-ui-react";

export default function Footer() {
    return (
        <div>
            <Segment
                color="black"
                inverted
                vertical
                style={{
                    padding: "4em 0em",
                    position: "static",
                    bottom: 0,
                    width: "100%",
                    marginTop: "20em"
                }}
            >
                <Container>
                    <Grid divided inverted  verticalAlign={"middle"}>
                        <Grid.Row>
                            <Grid.Column width={6} style={{marginBottom: "0.5em"}}>
                                <List link inverted>
                                    <List.Item as="a">About</List.Item>
                                    <List.Item as="a">Contact Us</List.Item>
                                </List>
                            </Grid.Column>
                            <Grid.Column width={10}  floated={"right"}>
                                <Header style={{ marginTop: "0em" }} as="h2">
                                    <Header.Content>
                                        <font color="#fffaf0">
                                            E-Crow by Berkcan Altungöz
                                        </font>
                                    </Header.Content>
                                </Header>
                                <Container>
                                    © 2022 E-Crow an Escrow Service For Everyone
                                </Container>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                </Container>
            </Segment>
        </div>
    );
}
