import axios from "axios";

export default class BaseUserService{
    getAll() {
        axios.get("http://localhost:8080/api/baseUser/getAll")
    }

    getById(id) {
        axios.get(`http://localhost:8080/api/baseUser/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        axios.get(`http://localhost:8080/api/baseUser/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        axios.get(`http://localhost:8080/api/baseUser/getByEmail?email=ad${email}`)
    }
}