import axios from "axios";

export default class PaymentMethod{
    getAll() {
        axios.get("http://localhost:8080/api/paymentMethod/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/paymentMethod/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        axios.get(`http://localhost:8080/api/paymentMethod/getByFkCustomerId?customerId=${customerId}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/paymentMethod/add", values)
    }

    deleteById(id) {
        axios.delete(`http://localhost:8080/api/paymentMethod/deleteById?id=${id}`)
    }

    update(values){
        axios.patch("http://localhost:8080/api/paymentMethod/update", values)
    }
}