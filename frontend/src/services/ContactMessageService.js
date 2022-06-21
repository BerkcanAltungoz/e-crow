import axios from "axios";

export default class ContactMessageService{

    getAll(){
        axios.get("http://localhost:8080/api/contactMessage/getAll")
    }

    getById(id){
        axios.get(`http://localhost:8080/api/contactMessage/getById?id=${id}`)
    }

    deleteAll(){
        axios.delete("http://localhost:8080/api/contactMessage/deleteAll")
    }

    deleteById(id){
        axios.delete(`http://localhost:8080/api/contactMessage/deleteById?id=${id}`)
    }

    add(values){
        axios.post("http://localhost:8080/api/contactMessage/add", values)
    }
}