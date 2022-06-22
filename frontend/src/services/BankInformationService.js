import axios from "axios";

export default class BankInformationService{

    getAll(){
        return axios.get("http://localhost:8080/api/bankInformation/getAll")
    }

    getById(id){
        return axios.get(`http://localhost:8080/api/bankInformation/getById?id=${id}`)
    }

    getByUserId(userId){
        return axios.get(`http://localhost:8080/api/bankInformation/getByFkUserId?userId=${userId}`)
    }

    deleteById(id){
        return axios.delete(`http://localhost:8080/api/bankInformation/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/bankInformation/add", values)
    }

    update(values){
        return axios.patch("http://localhost:8080/api/bankInformation/update", values)
    }
}