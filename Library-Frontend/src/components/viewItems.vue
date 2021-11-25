import Vue from 'vue'

<template>
    <div id="viewItems">
        <div class="header">
            <h3>View Items</h3>
        </div>
        <md-field class="select">
            <label for="itemSelected">Item Type</label>
            <md-select v-model="itemSelected" class="itemSelect">
                <md-option value="book">Book</md-option>
                <md-option value="movie">Movie</md-option>
                <md-option value="music">Music</md-option>
                <md-option value="newspaper">Newspaper</md-option>
            </md-select>
        </md-field>
        <div v-if="itemSelected == 'book'">
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Book</md-table-cell>
                    <md-table-cell class="th">Author</md-table-cell>
                    <md-table-cell class="th">Date Published</md-table-cell>
                    <md-table-cell class="th">Genre</md-table-cell>
                    <md-table-cell class="th">Publisher</md-table-cell>
                </md-table-row>
                <md-table-row v-for="book in books" :key="book.name" class="tr" md-selectable="single" @click="updateBookFields(book)">
                    <md-table-cell>{{ book.name }}</md-table-cell>
                    <md-table-cell> {{ book.author }}</md-table-cell>
                    <md-table-cell>{{ book.datePublished }}</md-table-cell>
                    <md-table-cell>{{ book.genre }}</md-table-cell>
                    <md-table-cell>{{ book.publisher }}</md-table-cell>
                </md-table-row>
            </md-table>
            <md-card class="md-primary">
                <div class="inputLine">
                    <md-field>
                        <label>Name</label>
                        <md-input v-model="title" placeholder="Title"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field>
                        <label>Author</label>
                        <md-input v-model="author" placeholder="Author"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field class="bookDate">
                        <label>Date Published</label>
                        <md-input v-model="bookDatePublished" placeholder="Date Published (yyyy-mm-dd)"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                <md-field class="bookGenre">
                    <label>Genre</label>
                    <md-input v-model="genre" placeholder="Genre"></md-input>
                </md-field>
                </div>
                <div class="inputLine">
                <md-field class="bookPublisher">
                    <label>Publisher</label>
                    <md-input v-model="publisher" placeholder="Publisher"></md-input>
                </md-field>
                </div>
            </md-card>
            <div>
                <b-button pill variant="outline-secondary" class="buttons">Add Book</b-button>
                <b-button pill variant="outline-secondary" class="buttons">Delete Book</b-button>
            </div>
        </div>
        <div v-else-if="itemSelected == 'movie'">
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Movie</md-table-cell>
                    <md-table-cell class="th">Director</md-table-cell>
                    <md-table-cell class="th">Running Time</md-table-cell>
                    <md-table-cell class="th">Rating</md-table-cell>
                    <md-table-cell class="th">Film Distributor</md-table-cell>
                    <md-table-cell class="th">Release Date</md-table-cell>
                </md-table-row>
                <md-table-row v-for="movie in movies" :key="movie.name" class="tr" md-selectable="single">
                    <md-table-cell>{{ movie.name }}</md-table-cell>
                    <md-table-cell> {{ movie.director }}</md-table-cell>
                    <md-table-cell>{{ movie.runningTime }}</md-table-cell>
                    <md-table-cell>{{ movie.rating }}</md-table-cell>
                    <md-table-cell>{{ movie.filmDistributor }}</md-table-cell>
                    <md-table-cell>{{ movie.releaseDate }}</md-table-cell>
                </md-table-row>
            </md-table>
            <md-card class="md-primary">
                <div class="inputLine1">
                    <md-field>
                        <label>Tile</label>
                        <md-input v-model="movieTitle" placeholder="Title"></md-input>
                    </md-field>
                </div>
                <div class="inputLine1">
                    <md-field>
                        <label>Director</label>
                        <md-input v-model="director" placeholder="Director"></md-input>
                    </md-field>
                </div>
                <div class="inputLine1">
                    <md-field>
                        <label>Running Time</label>
                        <md-input v-model="runningTime" placeholder="Running Time"></md-input>
                    </md-field>
                </div>
                <div class="inputLine1">
                    <md-field>
                        <label>Rating</label>
                        <md-input v-model="rating" placeholder="Rating"></md-input>
                    </md-field>
                </div>
                <div class="inputLine1">
                    <md-field>
                        <label>Film Distributor</label>
                        <md-input v-model="filmDistributor" placeholder="Film Distributor"></md-input>
                    </md-field>
                </div>
                <div class="inputLine1">
                    <md-field class="releaseDate">
                        <label>Release Date</label>
                        <md-input v-model="movieReleaseDate" placeholder="Release Date (yyyy-mm-dd)"></md-input>
                    </md-field>
                </div>
            </md-card>
            <div>
                <b-button pill variant="outline-secondary" class="buttons">Add Movie</b-button>
                <b-button pill variant="outline-secondary" class="buttons">Delete Movie</b-button>
            </div>
        </div>
        <div v-else-if="itemSelected == 'music'">
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Music</md-table-cell>
                    <md-table-cell class="th">Musician</md-table-cell>
                    <md-table-cell class="th">Record Label</md-table-cell>
                    <md-table-cell class="th">Release Date</md-table-cell>
                </md-table-row>
                <md-table-row v-for="m in music" :key="m.name" class="tr" md-selectable="single">
                    <md-table-cell>{{ m.name }}</md-table-cell>
                    <md-table-cell> {{ m.musician }}</md-table-cell>
                    <md-table-cell>{{ m.recordLabel }}</md-table-cell>
                    <md-table-cell>{{ m.releaseDate }}</md-table-cell>
                </md-table-row>
            </md-table>
            <md-card class="md-primary">
                <div class="inputLine">
                    <md-field>
                        <label>Name</label>
                        <md-input v-model="musicName" placeholder="Name"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field>
                        <label>Musician</label>
                        <md-input v-model="musician" placeholder="Musician"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field>
                        <label>Record Label</label>
                        <md-input v-model="recordLabel" placeholder="Record Label"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field class="releaseDate">
                        <label>Release Date</label>
                        <md-input v-model="musicReleaseDate" placeholder="Release Date (yyyy-mm-dd)"></md-input>
                    </md-field>
                </div>
            </md-card>
            <div>
                <b-button pill variant="outline-secondary" class="buttons">Add Music</b-button>
                <b-button pill variant="outline-secondary" class="buttons">Delete Music</b-button>
            </div>
        </div>
        <div v-else-if="itemSelected == 'newspaper'">
            <md-table md-card class="table">
                <md-table-row>
                    <md-table-cell class="th">Newpaper</md-table-cell>
                    <md-table-cell class="th">Headline</md-table-cell>
                    <md-table-cell class="th">Date Published</md-table-cell>
                </md-table-row>
                <md-table-row v-for="newspaper in newspapers" :key="newspaper.name" class="tr" md-selectable="single">
                    <md-table-cell>{{ newspaper.name }}</md-table-cell>
                    <md-table-cell> {{ newspaper.headline }}</md-table-cell>
                    <md-table-cell>{{ newspaper.datePublished }}</md-table-cell>
                </md-table-row>
            </md-table>
            <md-card class="md-primary">
                <div class="inputLine">
                    <md-field>
                        <label>Newspaper</label>
                        <md-input v-model="newspaper" placeholder="Newspaper"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field>
                        <label>Headline</label>
                        <md-input v-model="headline" placeholder="Headline"></md-input>
                    </md-field>
                </div>
                <div class="inputLine">
                    <md-field class="releaseDate">
                        <label>Date Published</label>
                        <md-input v-model="newspaperDatePublished" placeholder="Date Published (yyyy-mm-dd)"></md-input>
                    </md-field>
                </div>
            </md-card>
            <div>
                <b-button pill variant="outline-secondary" class="buttons">Add Newspaper</b-button>
                <b-button pill variant="outline-secondary" class="buttons">Delete Newspaper</b-button>
            </div>
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

    .th {
        background-color: #c09f80;
    }

    .tr {
        background-color: #d7cec7;
    }

    .table {
        margin-left: auto;
        margin-right: auto;
        width: 75%;
    }

    .tr:hover {
        background-color: #c09f80;
    }

    .inputLine {
        display: inline-block;
    }

    .inputLine1 {
        display: inline-block;
        width: 15%
    }

    .md-primary {
        margin-left: auto;
        margin-right: auto;
        width: 75%;
        background-color: #d7cec7;
    }

    .bookDate {
        width: 120%;
    }

    .bookGenre {
        position: relative;
        left: 60%;
    }

    .bookPublisher {
        position: relative;
        left: 50%;
    }

    .buttons {
        position: relative;
        top: 10px;
        display: inline-block;
    }

    .releaseDate {
        width: 120%;
    }
</style>

<script src="./viewItems.js">
</script>