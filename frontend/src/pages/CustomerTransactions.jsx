import {useSelector} from "react-redux";
import {Link} from "react-router-dom";
import {Button, Card, CardGroup, Header, Image, Table} from "semantic-ui-react";
import React, {useEffect, useState} from "react";
import ItemTransactionService from "../services/ItemTransactionService";

export default function CustomerTransactions() {
    const transactionService = new ItemTransactionService();
    const userProps = useSelector(state => state?.user?.userProps)

    const [buyerTransactions, setBuyerTransactions] = useState([]);
    const [sellerTransactions, setSellerTransactions] = useState([]);



    useEffect(() => {
        let buyerExists = false
        let sellerExists = false
        if (typeof userProps?.user?.id !== 'undefined') {
            // transactionService.existsByBuyerId(userProps?.user?.id).then(result => buyerExists = result.data.data)
            transactionService.existsBySellerId(userProps?.user?.id).then(result => sellerExists = result.data.data)
            transactionService.existsByBuyerId(userProps?.user?.id)
                .then((result) => {
                buyerExists = result.data.data
            })
            transactionService.existsBySellerId(userProps?.user?.id)
                .then((result) => {
                    sellerExists = result.data.data
                })

            // TODO: FIX THIS
            console.log(buyerExists)
            if (buyerExists === false) {
                transactionService.getByBuyerId(userProps?.user?.id)
                    .then(result => {
                        setBuyerTransactions(result.data.data)
                        console.log(result.data.data)
                    })
                    .catch((result) => {
                        console.log("Buyer " + result.response.data.message)
                    })

            }

            if (sellerExists === false) {
                transactionService.getBySellerId(userProps?.user?.id)
                    .then(result => setSellerTransactions(result.data.data))
                    .catch((result) => {
                        console.log(result.response.status)
                        console.log("Seller " + result.response.data.message)
                    })
            }
        }
    }, [userProps?.user?.id])


    return (
        <div>
            <Button color="black" as={Link} to={"/transactions/new"} size={"huge"} style={{marginTop: "2em"}}>
                New Transaction
            </Button>

            <Header as="h1" color="black" textAlign="center" style={{marginTop: "1em", marginBottom: "1em"}}>
                <Image
                    src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                Buyer Transactions
            </Header>
            {buyerTransactions.map(buyerTransaction => (
                <CardGroup key={buyerTransaction.id}>
                    <Card fluid color={"black"} style={{marginTop: "1em"}}>
                        <Card.Content>
                            <Card.Description>
                                <Table celled color={"black"}>
                                    <Table.Body>
                                        <Table.Row>
                                            <Table.Cell>
                                                {"Buyer: " + buyerTransaction?.fkBuyer?.name + " " + buyerTransaction?.fkBuyer?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"}
                                                        disabled style={{marginLeft: "1em"}}>Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Seller: " + buyerTransaction?.fkSeller?.name + " " + buyerTransaction?.fkSeller?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled >Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Employee: " + buyerTransaction?.fkEmployee?.name + " " +  buyerTransaction?.fkEmployee?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"}
                                                        disabled style={{marginLeft: "1em"}}>Message</Button>
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Item: " + buyerTransaction?.itemName}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Item Price: " + buyerTransaction?.itemPrice}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Total Price: " + (buyerTransaction?.itemPrice + buyerTransaction?.employeeFee)}
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Status: " + buyerTransaction?.fkStatus?.name}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Location: " + buyerTransaction?.fkEmployee?.fkCity?.name + ", " + buyerTransaction?.fkEmployee?.fkTown?.name}
                                            </Table.Cell>
                                            <Table.Cell textAlign={"center"}>
                                                <Button color="black" as={Link} to={"/message"} disabled>Requirements</Button>
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
                        <Card.Content description={buyerTransaction?.details}/>
                    </Card>
                </CardGroup>
            ))}


            <Header as="h1" color="black" textAlign="center" style={{marginTop: "1em", marginBottom: "1em"}}>
                <Image
                    src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                Seller Transactions
            </Header>
            {sellerTransactions.map(sellerTransaction => (
                <CardGroup>
                    <Card fluid color={"black"} style={{marginTop: "1em"}}>
                        <Card.Content>
                            <Card.Description>
                                <Table celled color={"black"}>
                                    <Table.Body>
                                        <Table.Row>
                                            <Table.Cell>
                                                {"Buyer: " + sellerTransaction?.fkBuyer?.name + " " + sellerTransaction?.fkBuyer?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Seller: " + sellerTransaction?.fkSeller?.name + " " + sellerTransaction?.fkSeller?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Employee: " + sellerTransaction?.fkEmployee?.name + " " + sellerTransaction?.fkEmployee?.surname}
                                                <Button color="black" as={Link} to={"/message"} size={"small"} style={{marginLeft: "1em"}}
                                                        disabled>Message</Button>
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Item: " + sellerTransaction?.itemName}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Item Price: " + sellerTransaction?.itemPrice}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Total Price: " + (sellerTransaction?.itemPrice + sellerTransaction?.employeeFee)}
                                            </Table.Cell>
                                        </Table.Row>

                                        <Table.Row>
                                            <Table.Cell>
                                                {"Status: " + sellerTransaction?.fkStatus?.name}
                                            </Table.Cell>
                                            <Table.Cell>
                                                {"Location: " + sellerTransaction?.fkEmployee?.fkCity?.name + ", " + sellerTransaction?.fkEmployee?.fkTown?.name}
                                            </Table.Cell>
                                            <Table.Cell>
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
                        <Card.Content description={sellerTransaction?.details}/>
                    </Card>
                </CardGroup>
            ))}
        </div>
    )
}