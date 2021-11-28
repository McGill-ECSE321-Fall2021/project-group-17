import Vue from 'vue'
<template>
  <div id="app">
    <div class="login-page">
      <div class="container">
        <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
          <div class="card login">
            <h1>Log In</h1>
            <form class="login-info">
              <input v-model="userLogin" class="login-input" placeholder="Enter Username" required>
              <input v-model="passwordLogin" type="password" class="login-input" placeholder="Enter Password" required>
            </form>
            <b-button size="sm" class="btn" v-bind:disabled="!userLogin" @click="logIn">Login</b-button>
            <p>
              <span v-if="error" style="color:red">Error: {{errorLogin}}</span>
            </p>

            <div class="acct">Don't have an account?<router-link to="/signup"> Sign up here</router-link></div>

          </div>

        </div>
      </div>

    </div>
  </div>
</template>

<script src="./login.js">
</script>

<style scoped>

p {
  line-height: 1rem;
}

.card {
  padding: 20px;
  font-family: monospace;
  color: #76323F;
  background: #D7CEC7;
  align-items: center;

}

.login-input {
  margin-top: 15px;
}

.btn {
  width: 100px;
  height: 25px;
  margin-bottom: 20px;
  margin-top: 20px;
  color: #D7CEC7;
  background: #76323F;
  align-items: center;
}

.login-page {
  align-items: center;
  display: flex;
  height: 100vh;
  background: url(https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MTB8fHxlbnwwfHx8fA%3D%3D&w=1000&q=80);
}

h1 {
  margin-bottom: 20px;
}


</style>

