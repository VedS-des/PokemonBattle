# PokemonBattle — Project Master Blueprint

## Team

Vedansh — Frontend + Integration
Nyasa — Pokemon/Data/Model
Ankit — Battle Engine + AI

## Player Pokemon

1. Gardevoir — Psychic/Fairy
2. Charizard — Fire/Flying
3. Lucario — Fighting/Steel
4. Gengar — Ghost/Poison
5. Dragonite — Dragon/Flying
6. Milotic — Water

## Opponent Trainers

Red — Easy
- Lapras
- Snorlax
- Charizard

Steven — Medium
- Metagross
- Claydol
- Cradily

Cynthia — Hard
- Togekiss
- Garchomp
- Spiritomb

## Battle

- Fixed 3v3 battle
- Player selects exactly 3 Pokemon from 6
- Each Pokemon has exactly 4 fixed moves
- Turn-based battle
- Enemy Pokemon are not revealed before battle
- Enemy Pokemon appear when sent into battle
- Battle ends when all 3 Pokemon on one side faint
- Red = Easy
- Steven = Medium
- Cynthia = Hard

## Game Flow

Intro
↓
Click to Start
↓
Player Trainer Sprite
(Boy/Girl)
↓
Opponent Selection
↓
Pokemon Team Selection
↓
3v3 Battle
↓
Win/Lose Screen

## After Battle

Try Again
→ Team Selection → Battle

Go to Main Menu
→ Opponent Selection → Team Selection → Battle

Player's selected trainer sprite is retained.

## Rules

- This document is the single source of truth.
- No teammate changes Pokemon, teams, moves, stats, or types independently.
- All final data must be agreed upon by the team.
- No extra game features will be added without team agreement.
