from DominionAnalyser.Match.Player import Player
from DominionAnalyser.Match.Log import Log


class Match:
    """the class representing the Match

    the Match is composed by all elements present in the game log"""

    def __init__(self, document):

        #the log id on the mongoDB database.
        self.ident = document.get('_id')

        #list of the winners .
        self.winners = document.get('winners')

        #list of empty piles on the game.
        self.cardsGonne = document.get('cardsgonne')

        #list of cards available on the game.
        self.market = document.get('market')

        #list of all the players on the match.
        self.players = [Player(p) for p in document.get('players')]

        #list of cards on the trash.
        self.trash = document.get('trash')

        #date and time of the game.
        self.dateTime = document.get('date')

        #the difference between the highest and lowest ELO in the match.
        self.eloGap = document.get('eloGap')

        #the step by step of all moves done in the game.
        self.log = Log(document.get('log'))

        #the name of the parsed file.
        self.fileName = document.get('filename')

    def get_player(self, player_name):
        for p in self.players:
            if p.playerName == player_name:
                return p

    def toDoc(self):
        """save the object into the database"""
        document = {"date": self.dateTime,
                    "filename": self.fileName,
                    "eloGap": self.eloGap,
                    "winners": self.winners,
                    "cardsgonne": self.cardsGonne,
                    "market": self.market,
                    "trash": self.trash,
                    "players": [p.toDoc() for p in self.players],
                    "log": [l.to_doc() for l in self.log.turns]}
        return document
