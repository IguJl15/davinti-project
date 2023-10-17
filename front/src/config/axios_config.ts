import axios from "axios";

const Axios = axios.create({
     headers: {
          'Content-Type': 'application/json',
     },

})

export { Axios }