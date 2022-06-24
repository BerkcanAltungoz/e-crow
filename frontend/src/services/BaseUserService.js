import axios from "axios";

export default class BaseUserService{
    getAll() {
        return axios.get("http://localhost:8080/api/baseUser/getAll")
    }

    getById(id) {
        return axios.get(`http://localhost:8080/api/baseUser/getById?id=${id}`)
    }

    getByPhoneNumber(phoneNumber) {
        return axios.get(`http://localhost:8080/api/baseUser/getByPhoneNumber?phoneNumber=${phoneNumber}`)
    }

    getByEmail(email) {
        return axios.get(`http://localhost:8080/api/baseUser/getByEmail?email=ad${email}`)
    }
}