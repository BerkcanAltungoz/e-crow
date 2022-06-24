import CityService from "../services/CityService";
import TownService from "../services/TownService";
import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {Button, Card, Form, Grid, Header, Image, Segment} from "semantic-ui-react";
import EmployeeService from "../services/EmployeeService";

export default function MainPage() {
    const employeeService = new EmployeeService();
    const cityService = new CityService();
    const townService = new TownService();

    const [employees, setEmployees] = useState([]);
    const [cities, setCities] = useState([]);
    const [cityId, setCityId] = useState(0)

    const [towns, setTowns] = useState([]);
    const [townId, setTownId] = useState(0);

    let firstChange = true;
    useEffect(() => {
        cityService.getAll().then(result => setCities(result.data.data))
    }, [])

    useEffect(() => {
        if (cityId !== 0) {
            townService.getByCityId(cityId).then(result => setTowns(result.data.data))
        }
    }, [cityId])

    useEffect(() => { //TODO: GET ACTIVE EMPLOYEES
        if (townId !== 0 && firstChange) {
            setEmployees([])
            firstChange = false
            employeeService.getByTownId(townId).then(result => setEmployees(result.data.data))
        } else if (townId !== 0 && !firstChange) {
            employeeService.getByTownId(townId).then(result => setEmployees(result.data.data))
        } else {
            employeeService.getAll().then(result => setEmployees(result.data.data))
        }
    }, [townId])

    const cityOptions = [];
    cities.map((city) => (cityOptions.push({
        key: city.id,
        text: city.name,
        value: city.id,
    })));

    const townOptions = [];
    towns.map((town) => (townOptions.push({
        key: town.id,
        text: town.name,
        value: town.id,
    })));

    const handleChangeCity = (value) => {
        setCityId(value)
        // console.log("City Id: " + value)
    }
    const handleChangeTown = (value) => {
        setTownId(value)
        // console.log("Town Id: " + value)
    }
    const handleClearFilter = () => {
        window.location.reload();
    };
    return (
        <div align={"center"}>
            <Grid stackable>
                <Grid.Column width={4} style={{marginBottom: "10em", marginTop: "9.5em"}}>
                    <Segment inverted>
                        <Form inverted>
                            <label><b>Filter Employees</b></label>
                            <Form.Select
                                name="city"
                                placeholder="City"
                                options={cityOptions}
                                onChange={(event, data) => handleChangeCity(data.value)}
                            />
                            <Form.Select
                                disabled={cityId === 0}
                                name="town"
                                placeholder="Town"
                                options={townOptions}
                                onChange={(event, data) => handleChangeTown(data.value)}
                            />
                            <Button circular fluid color="grey" content="Clear Filter"
                                    onClick={() => handleClearFilter()}/>
                        </Form>
                    </Segment>
                </Grid.Column>
                <Grid.Column width={12} style={{marginBottom: "10em", marginTop: "1.25em"}}>
                    <Header as="h1" color="black" textAlign="center" style={{marginTop: "1em", marginBottom: "1em"}}>
                        <Image
                            src="https://uxwing.com/wp-content/themes/uxwing/download/29-animals-and-birds/crow.png"/>
                        Available Employees
                    </Header>
                    <Card.Group style={{marginLeft: "7.7em"}}>
                        {employees.map(employee => (
                            <Card key={employee.id} color={"black"}>
                                <Card.Content>
                                    <Card.Header>{employee.name + " " + employee.surname}</Card.Header>
                                    <Card.Meta>{employee.fkCity.name + ", " + employee.fkTown.name}</Card.Meta>
                                    <Card.Description>{"Fee: " + employee.fee + "$"}</Card.Description>
                                    <Card.Description>{"Expertise: " + employee.expertise}</Card.Description>
                                    <Card.Description>{"Expertise Fee: " + employee.expertiseFee + "$"}</Card.Description>
                                </Card.Content>
                                <Card.Content extra>
                                    <div>
                                        <Button fluid color="black" as={Link} to={`/employee/${employee.id}`}>
                                            Show Details
                                        </Button>
                                    </div>
                                </Card.Content>
                            </Card>
                        ))}
                    </Card.Group>
                </Grid.Column>
            </Grid>
        </div>
    )
}