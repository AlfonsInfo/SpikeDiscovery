import axios from "axios"

const apiClient = axios.create({
   baseURL : "https://jsonplaceholder.org"
})  

const response = await apiClient.get("/posts")
console.log(response.data)
console.log("finish of the code")

