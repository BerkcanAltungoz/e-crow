import axios from "axios";

export default class CustomerService {

    getAll() {
        axios.get("http://localhost:8080/api/customer/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/customer/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        axios.get(`http://localhost:8080/api/customer/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        axios.get(`http://localhost:8080/api/customer/getByEmail?email=ad${email}`)
    }

    signIn(values) {
        axios.post("http://localhost:8080/api/customer/signIn", values)
    }

    deleteById(id) {
        axios.delete(`http://localhost:8080/api/customer/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/customer/add", values)
    }

    updateBalance(values){
        axios.patch("http://localhost:8080/api/customer/updateBalance", values)
    }

    updateBase(values){
        axios.patch("http://localhost:8080/api/customer/updateBase", values)
    }
}