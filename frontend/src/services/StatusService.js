import axios from "axios";

export default class StatusService{
    getAll() {
        axios.get("http://localhost:8080/api/status/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/status/getById?id=${id}`)
    }

    getByName(name){
        axios.get(`http://localhost:8080/api/status/getByName?name=${name}`)
    }
}