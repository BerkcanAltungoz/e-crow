import {useState} from "react";
import ItemTransactionService from "../services/ItemTransactionService";
import {useSelector} from "react-redux";
import React, {useEffect} from "react";
import {Button, Card, CardGroup, Header, Image, Table} from "semantic-ui-react";
import {Link} from "react-router-dom";

export default function EmployeeTransactions() {
    const transactionService = new ItemTransactionService();
    const userProps = useSelector(state => state?.user?.userProps)

    const [employeeTransactions, setEmployeeTransactions] = useState([]);


    useEffect(() => {
        let employeeExists = false
        if (typeof userProps?.user?.id !== 'undefined') {
           // transactionService.existsByEmployeeId(userProps?.user?.id).then(result => employeeExists = result.data.data)

            //TODO: FIX THIS
            if (employeeExists === false) {
                transactionService.getByEmployeeId(userProps?.user?.id)
                    .then(result => setEmployeeTransactions(result.data.data))
                    .catch((result) => {
                        console.log("Buyer " + result.response.data.message)
                    })
            }
        }
    }, [userProps?.user?.id])

    //TODO: EDIT STATUS
    return (
        <div>
            <Header as="h1" color="black" textAlign="center" style={{marginTop: "1em", marginBottom: "1em"}}>
                <Image
                    src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                Transactions
            </Header>
            {employeeTransactions.map(employeeTransaction => (
                <CardGroup key={employeeTransaction.id}>
                    <Card fluid color={"black"} style={{marginTop: "1em"}}>
                        <Card.Content>
                            <Card.Description>
                                <Table celled color={"black"}>
                                    <Table.Body>
                                        <Table.Row>
                                            <Table.Cell>
                                                {"Buyer: " + employeeTransaction.fkBuyer.name + " " + employeeTransaction.fkBuyer.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Seller: " + employeeTransaction.fkSeller.name + " " +employeeTransaction.fkSeller.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Employee: " + employeeTransaction.fkEmployee.name + " " + employeeTransaction.fkEmployee.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Item: " + employeeTransaction.itemName}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Item Price: " + employeeTransaction.itemPrice}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Total Price: " + (employeeTransaction.itemPrice + employeeTransaction.employeeFee)}
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Status: " + employeeTransaction.fkStatus.name}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Location: " + employeeTransaction.fkEmployee.fkCity.name + ", " + employeeTransaction.fkEmployee.fkTown.name}
                                            </Table.Cell>
                                            <Table.Cell textAlign={"center"}>
                                                <Button color="black" as={Link} to={"/message"}
                                                        disabled>Requirements</Button>
                                            </Table.Cell>
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
                        <Card.Content description={employeeTransaction.details}/>
                    </Card>
                </CardGroup>
            ))}
        </div>
    )
}