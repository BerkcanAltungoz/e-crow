import axios from "axios";

export default class CustomerService {

    getAll() {
        return axios.get("http://localhost:8080/api/customer/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/customer/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        return axios.get(`http://localhost:8080/api/customer/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        return axios.get(`http://localhost:8080/api/customer/getByEmail?email=ad${email}`)
    }

    signIn(values) {
        return axios.post("http://localhost:8080/api/customer/signIn", values)
    }

    deleteById(id) {
        return axios.delete(`http://localhost:8080/api/customer/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/customer/add", values)
    }

    updateBalance(values){
        return axios.patch("http://localhost:8080/api/customer/updateBalance", values)
    }

    updateBase(values){
        return axios.patch("http://localhost:8080/api/customer/updateBase", values)
    }
}