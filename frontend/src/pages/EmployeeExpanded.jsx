import {useSelector} from "react-redux";
import {Link, useParams} from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import {Button, Card, Header, Table} from "semantic-ui-react";
import React, {useEffect, useState} from "react";

export default function EmployeeExpanded(){

    let employeeId = useParams();
    console.log(employeeId)

    const userProps = useSelector(state => state?.user?.userProps)
    const [employee, setEmployee] = useState({});

    useEffect(() => {
        let employeeService = new EmployeeService();
        employeeService.getById(employeeId.id).then((result) => setEmployee(result.data.data));
    }, [employeeId]);

    // console.log(employee)
    return(
        <div>
            <Card fluid color={"black"} style={{marginTop:"5em"}}>
                <Card.Content>
                    <Card.Header style={{marginTop:"0.3em"}}>
                        {employee?.name + " " + employee?.surname}
                    </Card.Header>
                    <Card.Meta>
                        Verified Employee
                    </Card.Meta>
                    <Card.Description>
                        <Table celled color={"black"}>
                            <Table.Body>
                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>Location</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.fkTown?.name + ", " + employee?.fkCity?.name}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>Phone Number</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.phoneNumber}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>E-Mail</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.email}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>Has Expertise In</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.expertise}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>Default Fee</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.fee}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>
                                        <Header as="h4" >
                                            <Header.Content>Expertise Fee</Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{employee?.expertiseFee}</Table.Cell>
                                </Table.Row>
                            </Table.Body>
                        </Table>
                    </Card.Description>
                </Card.Content>
            </Card>

            <Card fluid color={"black"}>
                <Card.Content>
                    <Card.Header>
                        Details
                    </Card.Header>
                </Card.Content>
                <Card.Content description={employee?.description} />
            </Card>

            <div >
                <Button  color="black" as={Link} to={"/"} floated={"left"} size={"large"} >
                    Back
                </Button>

                <Button  color="black" as={Link} to={"/"} floated={"right"} size={"large"} >
                    Message
                </Button>
            </div>
        </div>
    )
}