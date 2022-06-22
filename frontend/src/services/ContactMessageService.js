import axios from "axios";

export default class ContactMessageService{

    getAll(){
        return axios.get("http://localhost:8080/api/contactMessage/getAll")
    }

    getById(id){
        return axios.get(`http://localhost:8080/api/contactMessage/getById?id=${id}`)
    }

    deleteAll(){
        return axios.delete("http://localhost:8080/api/contactMessage/deleteAll")
    }

    deleteById(id){
        return axios.delete(`http://localhost:8080/api/contactMessage/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/contactMessage/add", values)
    }
}