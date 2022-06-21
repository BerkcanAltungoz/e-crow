import axios from "axios";

export default class EmployeeService{

    getAll() {
        axios.get("http://localhost:8080/api/employee/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/employee/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        axios.get(`http://localhost:8080/api/employee/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        axios.get(`http://localhost:8080/api/employee/getByEmail?email=ad${email}`)
    }

    getByCityId(cityId){
        axios.get(`http://localhost:8080/api/employee/getByFkCityId?cityId=${cityId}`)
    }

    getByTownId(townId){
        axios.get(`http://localhost:8080/api/employee/getByFkTownId?townId=${townId}`)
    }

    signIn(values) {
        axios.post("http://localhost:8080/api/employee/signIn", values)
    }

    deleteById(id) {
        axios.delete(`http://localhost:8080/api/employee/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/employee/add", values)
    }

    updateBalance(values){
        axios.patch("http://localhost:8080/api/employee/updateBalance", values)
    }

    updateDetails(values){
        axios.patch("http://localhost:8080/api/employee/updateDetails", values)
    }

    updateBase(values){
        axios.patch("http://localhost:8080/api/employee/updateBase", values)
    }

}