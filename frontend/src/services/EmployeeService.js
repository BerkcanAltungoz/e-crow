import axios from "axios";

export default class EmployeeService{

    getAll() {
        return axios.get("http://localhost:8080/api/employee/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/employee/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        return axios.get(`http://localhost:8080/api/employee/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        return axios.get(`http://localhost:8080/api/employee/getByEmail?email=ad${email}`)
    }

    getByCityId(cityId){
        return axios.get(`http://localhost:8080/api/employee/getByFkCityId?cityId=${cityId}`)
    }

    getByTownId(townId){
        return axios.get(`http://localhost:8080/api/employee/getByFkTownId?townId=${townId}`)
    }

    getAllByAvailableIsTrue(){
        return axios.get("http://localhost:8080/api/employee/getAllByAvailableIsTrue")
    }

    getByCityIdAvailableIsTrue(cityId){
        return axios.get(`http://localhost:8080/api/employee/getByFkCityIdAndAvailableIsTrue?cityId=${cityId}`)
    }

    getByTownIdAvailableIsTrue(townId){
        return axios.get(`http://localhost:8080/api/employee/getByFkTownIdAndAvailableIsTrue?townId=${townId}`)
    }

    signIn(values) {
        return axios.post("http://localhost:8080/api/employee/signIn", values)
    }

    deleteById(id) {
        return axios.delete(`http://localhost:8080/api/employee/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/employee/add", values)
    }

    updateBalance(values){
        return axios.patch("http://localhost:8080/api/employee/updateBalance", values)
    }

    updateDetails(values){
        return axios.patch("http://localhost:8080/api/employee/updateDetails", values)
    }

    updateBase(values){
        return axios.patch("http://localhost:8080/api/employee/updateBase", values)
    }

}