import Vue from 'vue'

<template>
    <div id="returns">
        <h3>Returns</h3>
        <table class="center">
            <tr>
                <th>Item on Loan</th>
                <th>Customer</th>
                <th>Return Date</th>
            </tr>
            <tr v-for="loan in loans" :key="loan">
                <td>{{ loan.itemInstance.checkableItem.name }}</td>
                <td> {{ loan.customer.person.name }}</td>
                <td>{{ loan.returnDate }}</td>
            </tr>
        </table>
    </div>
</template>

<style>
    h3 {
        margin-left: auto;
        margin-right: auto;
    }

    .center {
        margin-left: auto;
        margin-right: auto;
    }

    th {
        padding: 20px;
    }
</style>

<script src="./returns.js">
</script>