package game;

import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameServer {
    private BoardManager boarda;

    static class PlayerData {
        private final int cellID;
        private final Player pl;

        PlayerData(Player p, int id) {
            cellID = id;
            pl = p;
        }

        public int getCellID() {
            return cellID;
        }

        public Player getPl() {
            return pl;
        }
    }

    private final Deque<PlayerData> que = new ArrayDeque<>();

    Skins skins = new Skins();

    public GameServer(List<Player> members) throws Exception {//init
        int IDs = 1;
        skins.addSkin(0, ".");
        for (var t : members) {
            que.addLast(new PlayerData(t, IDs));
            System.out.println("Player ID: " + IDs + " turn to choose symbol!");
            String tmp;
            while (true) {
                try {
                    tmp = t.askForSym();
                } catch (ClosedByInterruptException e) {
                    System.out.println("Player ID: " + que.getFirst().getCellID() + " ELIMINATED! Due causing a fatal error!");
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Player ID: " + que.getFirst().getCellID() + " ELIMINATED!");
                    que.pollFirst();
                    continue;
                }
                if (tmp == null) {
                    System.out.println("NOT ALLOWED! You're banned!");
                    que.pollLast();
                    break;
                } else if (!skins.addSkin(IDs, tmp)) {
                    System.out.println("Already used! Try another!");
                } else {
                    System.out.println("Got it");
                    break;
                }
            }
            IDs++;
        }
        que.forEach(t -> {
            t.getPl().getSkins(skins);
        });
    }

    private void sendDiffs(Move m, int code) {
        que.forEach(t -> {
            t.getPl().getDiff(m, code);
        });
    }

    public ResultOfGame play(BoardManager b) throws Exception {
        if (que.isEmpty()) {
            return new ResultOfGame("No players found :(", -1);
        }
        boarda = b;

        que.forEach(t -> {
            t.getPl().getBoard(boarda.getBoard());
        });

        while (true) {
            System.out.println("Player ID: " + que.getFirst().getCellID() + " turn!");
            if (que.size() == 1) {
                return new ResultOfGame("Player ID: " + que.getFirst().getCellID() + " Type: " + que.getFirst().getPl().getClass().getName() + " WON!", que.getFirst().getCellID());
            }

            Move move;
            try {
                move = que.getFirst().getPl().askToMove();
            } catch (ClosedByInterruptException e) {
                System.out.println("Player ID: " + que.getFirst().getCellID() + " ELIMINATED! Due causing a fatal error!");
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Player ID: " + que.getFirst().getCellID() + " ELIMINATED!");
                que.pollFirst();
                continue;
            }
            switch (boarda.turn(move, que.getFirst().getCellID()).getV()) {
                case WIN -> {
                    this.sendDiffs(move, que.getFirst().getCellID());
                    return new ResultOfGame("Player ID: " + que.getFirst().getCellID() + " Type: " + que.getFirst().getPl().getClass().getName() + " WON!", que.getFirst().getCellID());
                }
                case DRAW -> {
                    this.sendDiffs(move, que.getFirst().getCellID());
                    return new ResultOfGame("DRAW!", -1);
                }
                case WRONG_INPUT -> {
                    System.out.println("Player ID: " + que.getFirst().getCellID() + " ELIMINATED!");
                    que.pollFirst();
                }
                case NOTHING -> {
                    this.sendDiffs(move, que.getFirst().getCellID());
                    que.addLast(que.pollFirst());
                }
                case EXTRA_TURN -> {
                    this.sendDiffs(move, que.getFirst().getCellID());
                }
                default -> throw new RuntimeException("how?");
            }

        }
    }

}
