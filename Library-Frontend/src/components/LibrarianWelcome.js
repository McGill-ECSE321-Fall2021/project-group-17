
function setUsername(){
  var header = document.getElementById("HeadLibrarian")
  header.innerHTML = $cookie.get(username)
}
export default {
  name: "librarianWelcome",
  data() {
    return{
      name:""
    }
  }
}
