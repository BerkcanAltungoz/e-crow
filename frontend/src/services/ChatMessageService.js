import axios from "axios";

export default class ChatMessageService{
    getAll(){
        return axios.get("http://localhost:8080/api/chatMessage/getAll")
    }

    getById(id){
        return axios.get(`http://localhost:8080/api/chatMessage/getById?id=${id}`)
    }

    getByCustomerId(customerId){
        return axios.get(`http://localhost:8080/api/chatMessage/getByFkCustomerId?customerId=${customerId}`)
    }

    getByEmployeeId(employeeId){
        return axios.get(`http://localhost:8080/api/chatMessage/getByFkEmployeeId?employeeId=${employeeId}`)
    }

    getByCustomerIdAndEmployeeId(customerId, employeeId){
        return axios.get(`http://localhost:8080/api/chatMessage/getByFkCustomerIdAndFkEmployeeId?customerId=${customerId}&employeeId=${employeeId}`)
    }

    deleteById(id){
        return axios.delete(`http://localhost:8080/api/chatMessage/deleteById?id=${id}`)
    }

    add(values){
        return axios.post("http://localhost:8080/api/chatMessage/add", values)
    }


}