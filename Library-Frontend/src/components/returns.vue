import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'
import VueMaterial from 'vue-material'

Vue.use(VueMaterial)

<template>
    <div id="returns">
        <h3>Returns</h3>
        <table class="center">
            <tr>
                <th>Items on Loan</th>
                <th>Customer</th>
                <th>Return Date</th>
            </tr>
        </table>
    </div>
</template>

<style>
    h3 {
        margin-left: 50%;
        margin-right: 50%;
    }

    .center {
        margin-left: auto;
        margin-right: auto;
    }
</style>