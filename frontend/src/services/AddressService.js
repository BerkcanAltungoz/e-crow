import axios from "axios"

export default class AddressService{

    getAll(){
       return axios.get("http://localhost:8080/api/address/getAll")
    }

    getById(id){
        return axios.get(`http://localhost:8080/api/address/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        return axios.get(`http://localhost:8080/api/address/getByFkCustomerId?customerId=${customerId}`)
    }

    deleteById(id){
        return axios.delete(`http://localhost:8080/api/address/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/address/add", values)
    }

    update(values){
        return axios.patch("http://localhost:8080/api/address/update", values)
    }
}