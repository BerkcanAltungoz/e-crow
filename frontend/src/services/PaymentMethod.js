import axios from "axios";

export default class PaymentMethod{
    getAll() {
        return axios.get("http://localhost:8080/api/paymentMethod/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/paymentMethod/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        return axios.get(`http://localhost:8080/api/paymentMethod/getByFkCustomerId?customerId=${customerId}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/paymentMethod/add", values)
    }

    deleteById(id) {
        return axios.delete(`http://localhost:8080/api/paymentMethod/deleteById?id=${id}`)
    }

    update(values){
        return axios.patch("http://localhost:8080/api/paymentMethod/update", values)
    }
}