package misuy.domainModel;

public class Fight {
    private Team redTeam;
    private Team blueTeam;
    private int roundNumber;
    private boolean finished;
    private Team winner;

    public Fight(Team redTeam, Team blueTeam) {
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
        this.roundNumber = 0;
        this.finished = false;
        this.winner = null;
    }

    public void round() {
        if ((winner != null) | this.finished)
            return;

        if (redTeam.isDead() & blueTeam.isDead())
        {
            System.out.println("DRAW!");
            this.finished = true;
            return;
        }
        else if (redTeam.isDead()) {
            this.winner = this.blueTeam;
            System.out.println("BLUE TEAM WIN!");
            this.finished = true;
            return;
        }
        else if (blueTeam.isDead()) {
            this.winner = this.redTeam;
            System.out.println("RED TEAM WIN!");
            this.finished = true;
            return;
        }

        System.out.println(String.format("ROUND %d", this.roundNumber));

        if (roundNumber % 2 == 0) {
            this.redTeam.attack(blueTeam);
        }
        else {
            this.blueTeam.attack(redTeam);
        }
        this.roundNumber++;
    }

    public void simulate() {
        while ((this.winner == null) & !this.finished)
            this.round();
    }

    public Team getWinner() {
        return this.winner;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
