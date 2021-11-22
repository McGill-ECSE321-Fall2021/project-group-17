import Vue from 'vue'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default-dark.css'
<template>
    <div id="returns">
        <md-table md-card>
            <md-table-row>
                <md-table-head>Items on Loan</md-table-head>
                <md-table-head>Customer</md-table-head>
                <md-table-head>Return Date</md-table-head>
            </md-table-row>
        </md-table>
        <md-table md-card>
            <md-table-row>
                <md-table-head>Items on Hold</md-table-head>
                <md-table-head>Customer</md-table-head>
                <md-table-head>Last Pickup Day</md-table-head>
            </md-table-row>
        </md-table>
    </div>
</template>

<style>
</style>