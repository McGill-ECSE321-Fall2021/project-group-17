import Vue from 'vue'


<template>
    <div id="viewItems">
        <div class="header">
            <h3>View Items</h3>
        </div>
        <md-field class="select">
            <label for="itemSelected">Item Type</label>
            <md-select v-model="itemSelected">
                <md-option value="book">Book</md-option>
                <md-option value="movie">Movie</md-option>
                <md-option value="music">Music</md-option>
                <md-option value="newspaper">Newspaper</md-option>
            </md-select>
        </md-field>
        <div v-if="itemSelected == 'book'">
            
        </div>
        <div v-else-if="itemSelected == 'movie'">
            
        </div>
        <div v-else-if="itemSelected == 'music'">
            
        </div>
        <div v-else-if="itemSelected == 'newspaper'">
            
        </div>
    </div>
</template>

<style>
    .header {
        background-color: #76323f;
        height: 150%;
    }

    .select {
        margin-left: auto;
        margin-right: auto;
        width: 75%;
    }
</style>

<script src="./viewItems.js">
</script>