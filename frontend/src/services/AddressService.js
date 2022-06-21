import axios from "axios"

export default class AddressService{

    getAll(){
        axios.get("http://localhost:8080/api/address/getAll")
    }

    getById(id){
        axios.get(`http://localhost:8080/api/address/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        axios.get(`http://localhost:8080/api/address/getByFkCustomerId?customerId=${customerId}`)
    }

    deleteById(id){
        axios.delete(`http://localhost:8080/api/address/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/address/add", values)
    }

    update(values){
        axios.patch("http://localhost:8080/api/address/update", values)
    }
}